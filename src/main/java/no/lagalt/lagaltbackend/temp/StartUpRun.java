package no.lagalt.lagaltbackend.temp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.pojo.enums.AuthorityType;
import no.lagalt.lagaltbackend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import java.nio.charset.StandardCharsets;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartUpRun implements CommandLineRunner {

    
    private final UserRepository userRepository;

    @Override
    public void run(String... args) {


        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if ( !userRepository.existsByEmail("dev@lagalt.no") ) {

            AppUser user = AppUser.builder()
                    .full_name("Mr Lagalt")
                    .authorityType(AuthorityType.ADMIN)
                    .email("dev@lagalt.no")
                    .encryptedPassword(passwordEncoder.encode("fooboo123").getBytes(StandardCharsets.UTF_8))
                    .build();

            userRepository.save(user);

            log.debug("User {} created...", user.getFull_name());
        }
        if ( !userRepository.existsByEmail("dev@arif.se") ) {

            AppUser user = AppUser.builder()
                    .full_name("Mr Arif")
                    .authorityType(AuthorityType.USER)
                    .email("dev@arif.se")
                    .encryptedPassword(passwordEncoder.encode("fooboo123").getBytes(StandardCharsets.UTF_8))
                    .build();

            userRepository.save(user);

            log.debug("User {} created...", user.getFull_name());
        }
    }
}