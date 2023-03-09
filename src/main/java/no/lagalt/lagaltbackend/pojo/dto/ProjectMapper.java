package no.lagalt.lagaltbackend.pojo.dto;

import no.lagalt.lagaltbackend.pojo.entity.Project;

public interface ProjectMapper {

    ProjectDto toProjectDto(Project project);

    Project toProject(ProjectDto dto);


}
