package no.lagalt.lagaltbackend.repository;

import no.lagalt.lagaltbackend.pojo.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill,Integer> {

}
