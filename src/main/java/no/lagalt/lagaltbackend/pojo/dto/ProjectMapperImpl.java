package no.lagalt.lagaltbackend.pojo.dto;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.exception.ResourceNotFoundException;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.pojo.entity.Project;
import no.lagalt.lagaltbackend.pojo.entity.Skill;
import no.lagalt.lagaltbackend.repository.SkillRepository;
import no.lagalt.lagaltbackend.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectMapperImpl implements ProjectMapper {

    private final UserRepository userRepository;
    private final SkillRepository skillRepository;


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
        if (dto.getOwner() != 0) {
            AppUser owner = userRepository.findById(dto.getOwner())
                    .orElseThrow(() -> new ResourceNotFoundException("OWNER_DOES_NOT_EXIST"));
            project.setOwner(owner);
        }

        if (dto.getParticipants() != null && dto.getParticipants().size() > 0) {
            Set<AppUser> participants = new HashSet<>(userRepository.findAllById(dto.getParticipants()));
            System.out.println("I AM HERE");
            project.setParticipants(participants);
        }

        if (dto.getSkills() != null && dto.getSkills().size() > 0) {
            Set<Skill> skills = new HashSet<>(skillRepository.findAllById(dto.getSkills()));
            project.setSkills(skills);
        }
        return project;
    }
}
