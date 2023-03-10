package no.lagalt.lagaltbackend.service;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.exception.ResourceNotFoundException;
import no.lagalt.lagaltbackend.pojo.entity.Project;
import no.lagalt.lagaltbackend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

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
        foundProject.setSkills(project.getSkills());
        return projectRepository.save(foundProject);
    }

    @Override
    public void deleteById(Integer projectId) {
        projectRepository.deleteById(projectId);
    }

    private Project getProjectById(Integer integer) {
        return projectRepository.findById(integer)
                .orElseThrow(() -> new ResourceNotFoundException("PROJECT_NOT_EXIST"));
    }
}
