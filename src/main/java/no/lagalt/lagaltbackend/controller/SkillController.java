package no.lagalt.lagaltbackend.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.pojo.dto.SkillDto;
import no.lagalt.lagaltbackend.pojo.dto.SkillMapper;
import no.lagalt.lagaltbackend.pojo.entity.Skill;
import no.lagalt.lagaltbackend.service.SkillService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/skill")
@RequiredArgsConstructor
public class SkillController {

    private final SkillService skillService;

    private final SkillMapper skillMapper;


    @Operation(summary = "GET ALL SKILLS")
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<SkillDto> getAllSkills() {

        Collection<Skill> skillList = skillService.findAll();
        
        return skillList.stream()
                .map(skillMapper::toSkillDto).collect(Collectors.toList());
    }

}
