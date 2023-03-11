package no.lagalt.lagaltbackend.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<AppUser> userOpt = userRepository.findAppUserByEmail(email);
        if (userOpt.isPresent()) {
            AppUser appUser = userOpt.get();
            Charset charset = StandardCharsets.UTF_8;
            byte[] passEncrypted = appUser.getEncryptedPassword();
            String passString = new String(passEncrypted, charset);
            ArrayList<GrantedAuthority> authority = new ArrayList<>();
            authority.add(new SimpleGrantedAuthority("ROLE_" + appUser.getAuthorityType().toString()));
            return new User(appUser.getEmail(), passString, authority);
        } else {
            throw new UsernameNotFoundException("USER_EMAIL_NOT_FOUND");
        }
    }
}
