package no.lagalt.lagaltbackend.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.exception.ResourceNotFoundException;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.repository.UserRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserAuthorizer {
    private final UserRepository userRepository;

    public void loadUserByUsername(){
        // Retrieve user information from Keycloak server using JWT token
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = getEmailFromJwtToken(authentication);

        // Check if user exists in database
        AppUser user = userRepository.findByEmail(email);
        if (user == null) {
            // Create new user in database
            user = new AppUser();
            user.setEmail(email);
            userRepository.save(user);
        }

        // Return UserDetails object
//        new User(user.getEmail(), "", new ArrayList<>());
    }
    public AppUser getCurrentTokenUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof KeycloakPrincipal) {
            KeycloakPrincipal<KeycloakSecurityContext> keycloakPrincipal = (KeycloakPrincipal<KeycloakSecurityContext>) authentication.getPrincipal();
            AccessToken accessToken = keycloakPrincipal.getKeycloakSecurityContext().getToken();

            // Extract user information from the access token
            String username = accessToken.getPreferredUsername();
            String email = accessToken.getEmail();
            // Create a new AppUser object and populate it with the user information
            AppUser appUser = new AppUser();
            appUser.setFull_name(username);
            appUser.setEmail(email);
            return userRepository.save(appUser);
        } else {
            throw new ResourceNotFoundException("USER_MISSING");
        }
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


    private String getEmailFromJwtToken(Authentication authentication) {

        if (authentication instanceof PreAuthenticatedAuthenticationToken) {
            PreAuthenticatedAuthenticationToken token = (PreAuthenticatedAuthenticationToken) authentication;
            String jwtToken = (String) token.getPrincipal();
            String[] jwtParts = jwtToken.split("\\.");
            if (jwtParts.length != 3) {
                throw new BadCredentialsException("Invalid JWT token");
            }
            String decoded = new String(Base64.decodeBase64(jwtParts[1]));
            try {
                JsonNode jsonNode = new ObjectMapper().readTree(decoded);
                if (jsonNode.has("email")) {
                    return jsonNode.get("email").asText();
                }
            } catch (IOException e) {
                throw new BadCredentialsException("Invalid JWT token", e);
            }
        }
        throw new BadCredentialsException("Invalid authentication");
    }

}
