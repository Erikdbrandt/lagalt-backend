package no.lagalt.lagaltbackend.service;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public Collection<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser findById(Integer integer) {
        return null;
    }

    @Override
    public AppUser create(AppUser entity) {
        return null;
    }

    @Override
    public void update(AppUser entity) {

    }

    @Override
    public void deleteById(Integer integer) {

    }
}
