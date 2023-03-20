//package no.lagalt.lagaltbackend.security;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import no.lagalt.lagaltbackend.pojo.entity.AppUser;
//import no.lagalt.lagaltbackend.repository.UserRepository;
//import org.apache.tomcat.util.codec.binary.Base64;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//@Service
//@RequiredArgsConstructor
//public class CustomUserDetailsService implements UserDetailsService {
//
//    private final UserRepository userRepository;
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // Retrieve user information from Keycloak server using JWT token
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String email = getEmailFromJwtToken(authentication);
//
//        // Check if user exists in database
//        AppUser user = userRepository.findByEmail(email);
//        if (user == null) {
//            // Create new user in database
//            user = new AppUser();
//            user.setEmail(email);
//            userRepository.save(user);
//        }
//
//        // Return UserDetails object
//        return new User(user.getEmail(), "", new ArrayList<>());
//    }
//
//    private String getEmailFromJwtToken(Authentication authentication) {
//
//        if (authentication instanceof PreAuthenticatedAuthenticationToken) {
//            PreAuthenticatedAuthenticationToken token = (PreAuthenticatedAuthenticationToken) authentication;
//            String jwtToken = (String) token.getPrincipal();
//            String[] jwtParts = jwtToken.split("\\.");
//            if (jwtParts.length != 3) {
//                throw new BadCredentialsException("Invalid JWT token");
//            }
//            String decoded = new String(Base64.decodeBase64(jwtParts[1]));
//            try {
//                JsonNode jsonNode = new ObjectMapper().readTree(decoded);
//                if (jsonNode.has("email")) {
//                    return jsonNode.get("email").asText();
//                }
//            } catch (IOException e) {
//                throw new BadCredentialsException("Invalid JWT token", e);
//            }
//        }
//        throw new BadCredentialsException("Invalid authentication");
//    }
//
//
//}
