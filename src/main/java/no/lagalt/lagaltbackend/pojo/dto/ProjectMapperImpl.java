package no.lagalt.lagaltbackend.pojo.dto;

import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.pojo.entity.Project;
import no.lagalt.lagaltbackend.pojo.entity.Skill;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProjectMapperImpl implements ProjectMapper {


    @Override
    public ProjectDto toProjectDto(Project project) {

        ProjectDto dto = new ProjectDto();

        dto.setProject_id(project.getProject_id());
        dto.setTitle(project.getTitle());
        dto.setDescription(project.getDescription());
        dto.setTheme(project.getTheme());
        dto.setProject_status(project.getProject_status());
        dto.setProject_type(project.getProject_type());

        if (project.getOwner() != null) {
            dto.setOwner(project.getOwner().getUser_id());
        }
        if (project.getParticipants() != null) {
            List<Integer> appUserIds = project.getParticipants().stream()
                    .map(AppUser::getUser_id).collect(Collectors.toList());

            dto.setParticipants(appUserIds);
        }
        if (project.getSkills() != null) {
            List<Integer> appUserIds = project.getSkills().stream()
                    .map(Skill::getSkill_id).collect(Collectors.toList());

            dto.setSkills(appUserIds);
        }

        return dto;
    }

    @Override
    public Project toProject(ProjectDto dto) {

        Project project = new Project();

        project.setProject_id(dto.getProject_id());
        project.setTitle(dto.getTitle());
        project.setDescription(dto.getDescription());
        project.setTheme(dto.getTheme());
        project.setProject_status(dto.getProject_status());
        project.setProject_type(dto.getProject_type());


        return project;
    }
}
