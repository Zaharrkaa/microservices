package ru.zaharka.filters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import ru.zaharka.services.JWTService;

@Component
public class AuthFilter extends AbstractGatewayFilterFactory<AuthFilter.Config> {

    @Autowired
    public AuthFilter(JWTService jwtService, RouteFilter routeFilter) {
        super(Config.class);
        this.jwtService = jwtService;
        this.routeFilter = routeFilter;
    }

    private final RouteFilter routeFilter;

    private final JWTService jwtService;

    @Override
    public GatewayFilter apply(Config config) {
        System.out.println("проверка секьюрити");
        return ((((exchange, chain) -> {
            if(routeFilter.filter.test(exchange.getRequest())){
                if(!exchange.getRequest().getHeaders().containsKey("Authorization")){
                    throw new RuntimeException("Missing Authorization header");
                }
                String authorization = exchange.getRequest().getHeaders().get("Authorization").getFirst();
                System.out.println(authorization);
                if(authorization.startsWith("Bearer ") && authorization != null){
                    authorization = authorization.substring("Bearer ".length());
                    System.out.println(authorization);
                }
                try {
                    jwtService.parseToken(authorization);
                }
                catch(Exception e){
                    throw new RuntimeException("Invalid JWT");
                }
            }
            return chain.filter(exchange);
        })));

    }

    public static class Config{}
}
