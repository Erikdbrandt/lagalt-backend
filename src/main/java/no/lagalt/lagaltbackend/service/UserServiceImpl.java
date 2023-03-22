package no.lagalt.lagaltbackend.service;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.exception.ResourceNotFoundException;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.pojo.entity.Skill;
import no.lagalt.lagaltbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final SkillService skillService;



    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public AppUser findByEmail(String email) {

        if (userRepository.findByEmail(email) == null) {
            return null;
        }
        return userRepository.findByEmail(email);
    }

    @Override
    public AppUser updateUserSkillsById(Set<Integer> skillIDs, int userId) {
        AppUser user = getUserById(userId);

        Set<Skill> skillsToAdd = skillIDs.stream().map(skillId -> skillService.findById(skillId))
                .collect(Collectors.toSet());
        user.setSkills(skillsToAdd);

        for(Skill skill : skillsToAdd){
            Set<AppUser> skillUsers = skill.getUsers();
            skillUsers.add(user);
            skill.setUsers(skillUsers);
        }
        return userRepository.save(user);

    }

    @Override
    public Collection<AppUser> findAll() {
        return userRepository.findAll();
    }

    @Override
    public AppUser findById(Integer integer) {
        return getUserById(integer);
    }

    @Override
    public AppUser create(AppUser entity) { // need to implement passwordEncoder
        return userRepository.save(entity);
    }

    @Override
    public AppUser update(Integer userId, AppUser entity) {


        AppUser foundUser = getUserById(userId);
        foundUser.setFull_name(entity.getFull_name());
        foundUser.setEmail(entity.getEmail());
        foundUser.setPassword(entity.getPassword());
        foundUser.setAuthorityType(entity.getAuthorityType());
        foundUser.setUserVisibility(entity.getUserVisibility());

        foundUser.setSkills(entity.getSkills());



        System.out.println(entity.getSkills());

        System.out.println("heeeeeeeeeeeeeeeeeeeeej");

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
