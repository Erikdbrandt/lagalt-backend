package no.lagalt.lagaltbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.pojo.dto.ProjectDto;
import no.lagalt.lagaltbackend.pojo.dto.ProjectMapper;
import no.lagalt.lagaltbackend.pojo.entity.Project;
import no.lagalt.lagaltbackend.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    @Operation(summary = "GET ALL PROJECT")
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectDto> getAllProject() {
        Collection<Project> projects = projectService.findAll();
        return projects.stream()
                .map(projectMapper::toProjectDto)
                .collect(Collectors.toList());

    }

    @Operation(summary = "GET SINGLE PROJECT")
    @GetMapping("/{projectId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProjectDto getProjectById(@PathVariable("projectId") int projectId) {
        return projectMapper.toProjectDto(projectService.findById(projectId));
    }

    @Operation(summary = "CREATE PROJECT")
    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProjectDto create(@RequestBody ProjectDto projectDto) {
        Project project = projectMapper.toProject(projectDto);
        return projectMapper.toProjectDto(projectService.create(project));
    }


    @Operation(summary = "UPDATE SINGLE PROJECT")
    @PatchMapping("/update/{projectId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProjectDto updateProjectById(@RequestBody ProjectDto projectDto, @PathVariable("projectId") int projectId) {
        Project project = projectMapper.toProject(projectDto);
        return projectMapper.toProjectDto(projectService.update(projectId, project));
    }

    @Operation(summary = "DELETE SINGLE PROJECT")
    @DeleteMapping("/delete/{projectId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteProjectById(@PathVariable("projectId") int projectId) {
        projectService.deleteById(projectId);
        return "PROJECT_DELETED";
    }

}
