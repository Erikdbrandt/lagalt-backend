package no.lagalt.lagaltbackend.service.services;

import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.service.services.CrudService;

import java.util.Set;

public interface UserService extends CrudService<AppUser,Integer> {
    AppUser getCurrentTokenUser();

    AppUser addSkillsToUser(Set<Integer> skills, int userId);

    AppUser findByEmail(String email);


    AppUser updateUserSkillsById(Set<Integer> skillIDs, int userId);

}
