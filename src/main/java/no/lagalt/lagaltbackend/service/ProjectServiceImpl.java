package no.lagalt.lagaltbackend.service;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.pojo.entity.Project;
import no.lagalt.lagaltbackend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService{
    private final ProjectRepository projectRepository;
    @Override
    public Collection<Project> findAll() {
        return null;
    }

    @Override
    public Project findById(Integer integer) {
        return null;
    }

    @Override
    public Project create(Project entity) {
        return null;
    }

    @Override
    public Project update(Integer integer, Project entity) {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
