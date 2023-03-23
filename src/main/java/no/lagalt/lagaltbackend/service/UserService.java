package no.lagalt.lagaltbackend.service;

import no.lagalt.lagaltbackend.pojo.entity.AppUser;

import java.util.Set;

public interface UserService extends CrudService<AppUser,Integer>{

    AppUser findByEmail(String email);

    AppUser updateUserSkillsById(Set<Integer> skillIDs, int userId);
}
