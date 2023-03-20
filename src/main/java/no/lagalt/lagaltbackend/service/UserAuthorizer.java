package no.lagalt.lagaltbackend.service;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.exception.ResourceNotFoundException;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthorizer {
    private final UserRepository userRepository;


    public AppUser getCurrentTokenUser(){
        Optional<AppUser> user = userRepository.findAppUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        return user.orElseThrow(()->new ResourceNotFoundException("USER_MISSING"));
    }

    public String getCurrentUsername() {
        Optional<AppUser> user = userRepository.findAppUserByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        String resp = "Anonymous";
        if ( user.isPresent() ) {
            resp = user.get().getEmail();
        }
        return resp;
    }


    public Authentication getAuth(){
        return SecurityContextHolder.getContext().getAuthentication();
    }


    public String getRole(){
        return getAuth().getAuthorities().toArray()[0].toString();
    }

}
