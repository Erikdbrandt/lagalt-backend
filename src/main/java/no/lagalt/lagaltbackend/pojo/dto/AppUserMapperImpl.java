package no.lagalt.lagaltbackend.pojo.dto;

import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.pojo.entity.Project;
import no.lagalt.lagaltbackend.pojo.entity.Skill;

import no.lagalt.lagaltbackend.service.services.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AppUserMapperImpl implements AppUserMapper {


    @Autowired
    SkillService skillService;


    @Override
    public AppUserDto toAppUserDto(AppUser user) {
        AppUserDto dto = new AppUserDto();
        dto.setUser_id(user.getUser_id());
        dto.setFull_name(user.getFull_name());
        dto.setEmail(user.getEmail());
        dto.setAuthority_type(user.getAuthority_type());
        dto.setUserVisibility(user.getUserVisibility());
        if (user.getProjects() != null) {
            List<Integer> projectId = user.getProjects().stream()
                    .map(Project::getProject_id).collect(Collectors.toList());
            dto.setProjects(projectId);

        }

        if (user.getSkills() != null) {
            List<Integer> skillsId = user.getSkills().stream()
                    .map(Skill::getSkill_id).collect(Collectors.toList());
            dto.setSkills(skillsId);
        }
        return dto;
    }

    @Override
    public AppUser toAppUser(AppUserDto dto) {

        AppUser user = new AppUser();
        user.setUser_id(dto.getUser_id());
        user.setFull_name(dto.getFull_name());
        user.setEmail(dto.getEmail());
        user.setAuthority_type(dto.getAuthority_type());
        user.setUserVisibility(dto.getUserVisibility());

        if (dto.getSkills() != null) {
            Set<Skill> skills = dto.getSkills().stream().map(skillId -> skillService.findById(skillId))
                    .collect(Collectors.toSet());
            user.setSkills(skills);
        }
        return user;
    }
}
