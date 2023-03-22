package no.lagalt.lagaltbackend.service.services;

import no.lagalt.lagaltbackend.pojo.entity.Skill;
import no.lagalt.lagaltbackend.service.services.CrudService;

import java.util.List;

public interface SkillService extends CrudService<Skill,Integer> {
    List<String> findAllSkillNames();
}
