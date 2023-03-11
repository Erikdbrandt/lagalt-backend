package no.lagalt.lagaltbackend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import no.lagalt.lagaltbackend.exception.ConflictException;
import no.lagalt.lagaltbackend.exception.NotAuthorizedException;
import no.lagalt.lagaltbackend.exception.ResourceNotFoundException;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.pojo.enums.AuthorityType;
import no.lagalt.lagaltbackend.pojo.enums.UserVisibility;
import no.lagalt.lagaltbackend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final Authorizer authorizer;


    @Override
    public Collection<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser findById(Integer integer) {
        return getUserById(integer);
    }

    @Override
    public AppUser create(AppUser user) { // need to implement passwordEncoder
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String fullName = user.getFull_name();
        String email = user.getEmail();
        String password = user.getPassword();
        AuthorityType authorityType = user.getAuthorityType();
        UserVisibility userVisibility = user.getUserVisibility();

        log.debug("Checking auth");
        if (authorityType != null && authorityType.equals(AuthorityType.ADMIN) && !authorizer.getRole().equals("ROLE_ADMIN")) {
            throw new NotAuthorizedException("UNAUTHORIZED");
        }
        if (authorityType == null) {
            user.setAuthorityType(AuthorityType.USER);
        }
        log.debug("Checking if user exist");
        checkIfUserExist(user);

        user = AppUser.builder()
                .full_name(fullName)
                .email(email)
                .encryptedPassword(passwordEncoder.encode(password).getBytes(StandardCharsets.UTF_8))
                .authorityType(authorityType)
                .userVisibility(userVisibility)
                .build();
        return userRepository.save(user);
    }



    @Override
    public AppUser update(Integer userId, AppUser entity) {
        AppUser foundUser = getUserById(userId);
        foundUser.setFull_name(entity.getFull_name());
        foundUser.setEmail(entity.getEmail());
        foundUser.setPassword(entity.getPassword());
        foundUser.setAuthorityType(entity.getAuthorityType());
        foundUser.setUserVisibility(entity.getUserVisibility());
        return userRepository.save(foundUser);
    }

    @Override
    public void deleteById(Integer userId) {
        AppUser user = getUserById(userId);
        userRepository.delete(user);
    }

    private AppUser getUserById(Integer integer) {
        return userRepository.findById(integer).orElseThrow(() ->
                new ResourceNotFoundException("USER_NOT_EXIST.")
        );
    }

    @Override
    public AppUser getByUsername(String username) {
        Optional<AppUser> user = userRepository.findAppUserByEmail(username);
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("USER_DOES_NOT_EXIST");
        }
        return user.get();
    }

    @Override
    public AppUser getCurrentTokenUser() {
        AppUser currentTokenUser = authorizer.getCurrentTokenUser();
//        filterUserView(currentTokenUser);
        return currentTokenUser;
    }

//    private void filterUserView(AppUser currentTokenUser) {
//        if (!authorizer.getRole().contains("ADMIN")) {
//            log.debug("ROLE -> {}", authorizer.getRole());
//            currentTokenUser.setPassword(null);
//        }
//    }

    private void checkIfUserExist(AppUser user) {
        String email = user.getEmail();
        List<String> existUser = userRepository.existingUserEmail(email);
        if (existUser.contains(email)) {
            throw new ConflictException("USER_ALREADY_EXIST");
        }
    }
}
