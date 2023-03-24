package no.lagalt.lagaltbackend.service.implementation;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.exception.ResourceNotFoundException;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.pojo.entity.Project;
import no.lagalt.lagaltbackend.pojo.entity.Skill;
import no.lagalt.lagaltbackend.repository.UserRepository;

import no.lagalt.lagaltbackend.service.services.SkillService;
import no.lagalt.lagaltbackend.service.services.UserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final SkillService skillService;
    private final UserRepository userRepository;
    private final UserAuthorizer authorizer;


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


        Set<Skill> skillsToRemove = user.getSkills().stream()
                .filter(skill -> !skillsToAdd.contains(skill))
                .collect(Collectors.toSet());

        user.setSkills(skillsToAdd);



        // Update the skills with the new user
        for(Skill skill : skillsToAdd){
            Set<AppUser> skillUsers = skill.getUsers();
            skillUsers.add(user);
            skill.setUsers(skillUsers);
        }

        for(Skill skill : skillsToRemove){
            Set<AppUser> skillUsers = skill.getUsers();
            skillUsers.remove(user);
            skill.setUsers(skillUsers);
        }
        return userRepository.save(user);

    }

    @Override
    public Collection<Project> findProjectsByUserId(int userId) {
        AppUser user = findById(userId);
        return user.getProjects();
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

    @Override
    public AppUser getCurrentTokenUser() {
        authorizer.loadUserByUsername();
        return authorizer.getCurrentTokenUser();
    }

    @Override
    public AppUser addSkillsToUser(Set<Integer> skills, int userId) {
        AppUser user = findById(userId);
        Set<Skill> skillSet = skills.stream().map(skillService::findById)
                .collect(Collectors.toSet());
        user.setSkills(skillSet);
        for (Skill skill : skillSet) {
            Set<AppUser> users = skill.getUsers();
            users.add(user);
            skill.setUsers(users);
        }
        return userRepository.save(user);
    }

}
