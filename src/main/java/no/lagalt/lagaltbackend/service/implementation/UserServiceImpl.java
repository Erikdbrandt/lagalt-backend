package no.lagalt.lagaltbackend.service.implementation;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.exception.ResourceNotFoundException;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.repository.UserRepository;

import no.lagalt.lagaltbackend.service.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserAuthorizer authorizer;



    @Override
    public Collection<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser findById(Integer integer) {
        return getUserById(integer);
    }

    @Override
    public AppUser create(AppUser entity) {
        return userRepository.save(entity);
    }

    @Override
    public AppUser update(Integer userId, AppUser entity) {
        AppUser foundUser = getUserById(userId);
        foundUser.setFull_name(entity.getFull_name());
        foundUser.setEmail(entity.getEmail());
        foundUser.setAuthority_type(entity.getAuthority_type());
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
    public AppUser getCurrentTokenUser() {
        authorizer.loadUserByUsername();
        return authorizer.getCurrentTokenUser();
    }
}