package ru.zaharka.services;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import ru.zaharka.config.CustomDetails;
import ru.zaharka.entities.UserCredential;
import ru.zaharka.repos.UserCredentialRepository;

import java.util.Optional;

@Component
public class CustomDetailsService implements UserDetailsService {

    @Autowired
    UserCredentialRepository userCredentialRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserCredential> userCredential = userCredentialRepository.findByName(username);
        if (userCredential.isPresent()) {
            return new CustomDetails(userCredential.get());
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }


}
