package no.lagalt.lagaltbackend.service.services;


import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.pojo.entity.Project;

import java.util.Collection;
import java.util.Set;

public interface ProjectService extends CrudService<Project, Integer> {
    Collection<String> findProjectSkill(int projectId);

    AppUser findProjectOwner(int projectId);

    String findProjectOwnerName(int projectId);

    Project addSkillsToProject(Set<Integer> skills, int projectId);

    Project addParticipantsToProject(Set<Integer> participants, int projectId);

    Project addOwnerToProject(int owner, int projectId);

    Project joinProject(int participantId, int projectId);

    Project unJoinProject(int participantId, int projectId);
}
