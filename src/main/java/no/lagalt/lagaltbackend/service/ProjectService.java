package no.lagalt.lagaltbackend.service;


import no.lagalt.lagaltbackend.pojo.dto.AppUserDto;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.pojo.entity.Project;

import java.util.Collection;

public interface ProjectService extends CrudService<Project, Integer> {
    Collection<String> findProjectSkill(int projectId);

    AppUser findProjectOwner(int projectId);

    String findProjectOwnerName(int projectId);
}
