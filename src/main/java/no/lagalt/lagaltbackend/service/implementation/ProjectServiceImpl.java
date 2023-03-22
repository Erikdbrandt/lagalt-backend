package no.lagalt.lagaltbackend.service.implementation;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.exception.ResourceNotFoundException;
import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.pojo.entity.Project;
import no.lagalt.lagaltbackend.pojo.entity.Skill;
import no.lagalt.lagaltbackend.repository.ProjectRepository;
import no.lagalt.lagaltbackend.repository.SkillRepository;
import no.lagalt.lagaltbackend.repository.UserRepository;
import no.lagalt.lagaltbackend.service.services.ProjectService;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final SkillRepository skillRepository;

    @Override
    public Collection<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(Integer projectId) {
        return getProjectById(projectId);
    }

    @Override
    public Project create(Project project) {
        if (project.getOwner() != null) {
            AppUser owner = userRepository.findById(project.getOwner().getUser_id()).orElseThrow(() -> new ResourceNotFoundException("OWNER_DOES_NOT_EXIST"));
            project.setOwner(owner);
        }
        if (project.getSkills() != null && project.getSkills().size() > 0) {
            Set<Skill> skills = new HashSet<>(skillRepository.findAllById(project.getSkills().stream()
                    .map(Skill::getSkill_id)
                    .collect(Collectors.toSet())));
            project.setSkills(skills);
        }
        return projectRepository.save(project);
    }

    @Override
    public Project update(Integer projectId, Project project) {
        Project foundProject = getProjectById(projectId);
        foundProject.setTitle(project.getTitle());
        foundProject.setDescription(project.getDescription());
        foundProject.setTheme(project.getTheme());
        foundProject.setProject_status(project.getProject_status());
        foundProject.setProject_type(project.getProject_type());
        foundProject.setParticipants(project.getParticipants());
        return projectRepository.save(foundProject);
    }

    @Override
    public void deleteById(Integer projectId) {
        projectRepository.deleteById(projectId);
    }

    @Override
    public Collection<String> findProjectSkill(int projectId) {
        Project foundProject = getProjectById(projectId);
        Collection<Skill> skills = foundProject.getSkills();
        Collection<String> skillNames = new ArrayList<>();

        for (Skill skill : skills) {
            skillNames.add(skill.getName());
        }
        return skillNames;
    }

    @Override
    public AppUser findProjectOwner(int projectId) {
        Project foundProject = getProjectById(projectId);
        return foundProject.getOwner();
    }

    @Override
    public String findProjectOwnerName(int projectId) {
        Project foundProject = getProjectById(projectId);
        AppUser owner = foundProject.getOwner();
        return owner.getFull_name();
    }

    @Override
    public Project addSkillsToProject(int projectId, Set<Integer> skills) {

        Project project = findById(projectId);
        Set<Skill> skillSet = new HashSet<>();
        for (Integer skill : skills) {
            Skill skillById = skillRepository.findById(skill).orElseThrow(()-> new ResourceNotFoundException("SKILL_DOES_NOT_EXIST"));
            skillSet.add(skillById);
            project.setSkills(skillSet);
        }
        for (Skill skill : skillSet) {
            Set<Project> skillProjects = skill.getProjects();
            skillProjects.add(project);
            skillRepository.save(skill);
        }
        return projectRepository.save(project);
    }

    private Project getProjectById(Integer integer) {
        return projectRepository.findById(integer)
                .orElseThrow(() -> new ResourceNotFoundException("PROJECT_NOT_EXIST"));
    }
}
