package no.lagalt.lagaltbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.pojo.dto.*;
import no.lagalt.lagaltbackend.pojo.entity.Project;
import no.lagalt.lagaltbackend.service.services.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectService projectService;
    private final ProjectMapper projectMapper;

    private final AppUserMapper appUserMapper;

    @Operation(summary = "GET ALL PROJECT")
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<ProjectDto> getAllProject() {
        Collection<Project> projects = projectService.findAll();
        return projects.stream()
                .map(projectMapper::toProjectDto)
                .collect(Collectors.toList());

    }

    @Operation(summary = "GET PROJECT SKILLS")
    @GetMapping("/skills/{projectId}")
    @ResponseStatus(value = HttpStatus.OK)
//    @PreAuthorize("hasRole('adminn')")
    public Collection<String> getProjectSkillName(@PathVariable("projectId") int projectId) {
        return projectService.findProjectSkill(projectId);

    }

    @Operation(summary = "GET PROJECT OWNER")
    @GetMapping("/owner/{projectId}")
    @ResponseStatus(value = HttpStatus.OK)
//    @PreAuthorize("hasRole('adminn')")
    public AppUserDto getProjectOwnerName(@PathVariable("projectId") int projectId) {
        return appUserMapper.toAppUserDto(projectService.findProjectOwner(projectId));

    }

    @Operation(summary = "GET PROJECT OWNER")
    @GetMapping("/ownerName/{projectId}")
    @ResponseStatus(value = HttpStatus.OK)
//    @PreAuthorize("hasRole('adminn')")
    public String getProjectOwnerNameString(@PathVariable("projectId") int projectId) {
        return projectService.findProjectOwnerName(projectId);

    }

    //    @PreAuthorize("hasRole('offline_access')")
    @Operation(summary = "GET SINGLE PROJECT")
    @GetMapping("/{projectId}")
    @ResponseStatus(value = HttpStatus.OK)
    public ProjectDto getProjectById(@PathVariable("projectId") int projectId) {
        return projectMapper.toProjectDto(projectService.findById(projectId));
    }

    @Operation(summary = "CREATE PROJECT")
    @PostMapping("/create")
    @ResponseStatus(value = HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
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

    @Operation(summary = "ADD SKILL TO PROJECT")
    @PutMapping("/update/skills/{projectId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProjectDto addSkillsToProject(@RequestBody Set<Integer> skills, @PathVariable("projectId") int projectId) {
        return projectMapper.toProjectDto(projectService.addSkillsToProject(skills, projectId));
    }

    @Operation(summary = "DELETE SINGLE PROJECT")
    @DeleteMapping("/delete/{projectId}")
    @ResponseStatus(value = HttpStatus.OK)
    public String deleteProjectById(@PathVariable("projectId") int projectId) {
        projectService.deleteById(projectId);
        return "PROJECT_DELETED";
    }

}
