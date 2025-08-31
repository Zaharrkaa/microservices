package ru.zaharka.filters;


import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class RouteFilter {

    public final List<String> openApiEndpoints = List.of("/auth/register", "/auth/token");

    public Predicate<ServerHttpRequest> filter = req -> openApiEndpoints.stream().noneMatch(uri -> req.getURI().getPath().contains(uri));
}
