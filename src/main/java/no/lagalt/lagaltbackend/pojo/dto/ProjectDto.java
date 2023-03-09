package no.lagalt.lagaltbackend.pojo.dto;

import lombok.Data;
import no.lagalt.lagaltbackend.pojo.enums.ProjectStatusType;

import java.util.List;


@Data
public class ProjectDto {

    private int project_id;

    private String title;

    private String description;

    private String theme;

    private ProjectStatusType project_status;

    private String project_type;


    private int owner;

    private List<Integer> participants;

    private List<Integer> skills;

}
