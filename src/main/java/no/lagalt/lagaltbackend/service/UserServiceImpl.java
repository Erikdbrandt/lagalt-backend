package no.lagalt.lagaltbackend.service;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.exception.ResourceNotFoundException;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

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
        user.setEncryptedPassword(passwordEncoder.encode(user.getPassword()).getBytes(StandardCharsets.UTF_8));
        return userRepository.save(user);
    }

    @Override
    public AppUser update(Integer userId, AppUser user) {
        AppUser foundUser = getUserById(userId);
        foundUser.setFull_name(user.getFull_name());
        foundUser.setEmail(user.getEmail());
        foundUser.setEncryptedPassword(passwordEncoder.encode(user.getPassword()).getBytes(StandardCharsets.UTF_8));
        foundUser.setRoles(user.getRoles());
        foundUser.setUserVisibility(user.getUserVisibility());
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
}
