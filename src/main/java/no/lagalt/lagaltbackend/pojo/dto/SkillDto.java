package no.lagalt.lagaltbackend.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class SkillDto {


    private int skill_id;

    private String name;

    private String description;

    private List<Integer> users;

    private List<Integer> projects;
}
