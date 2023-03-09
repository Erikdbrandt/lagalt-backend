package no.lagalt.lagaltbackend.pojo.dto;

import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import no.lagalt.lagaltbackend.pojo.entity.Skill;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SkillMapperImpl implements SkillMapper {


    @Override
    public SkillDto toSkillDto(Skill skill) {

        SkillDto dto = new SkillDto();

        dto.setSkill_id(skill.getSkill_id());
        dto.setName(skill.getName());
        dto.setDescription(skill.getDescription());

        if(skill.getUsers() != null){

            List<Integer> appUserIds = skill.getUsers().stream()
                    .map(AppUser::getUser_id).collect(Collectors.toList());

            dto.setUsers(appUserIds);
        }

        return dto;
    }

    @Override
    public Skill toSkill(SkillDto dto) {

        Skill skill = new Skill();

        skill.setSkill_id(dto.getSkill_id());
        skill.setName(dto.getName());
        skill.setDescription(dto.getDescription());

        return skill;
    }
}

