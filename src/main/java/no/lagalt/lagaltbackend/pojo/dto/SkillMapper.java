package no.lagalt.lagaltbackend.pojo.dto;


import no.lagalt.lagaltbackend.pojo.entity.Skill;

public interface SkillMapper {

    SkillDto toSkillDto(Skill skill);


    Skill toSkill(SkillDto dto);

}
