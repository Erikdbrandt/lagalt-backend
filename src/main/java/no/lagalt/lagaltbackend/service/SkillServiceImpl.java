package no.lagalt.lagaltbackend.service;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.pojo.entity.Skill;
import no.lagalt.lagaltbackend.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService{


    private final SkillRepository skillRepository;

    @Override
    public Collection<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findById(Integer integer) {
        return null;
    }

    @Override
    public Skill create(Skill entity) {
        return null;
    }

    @Override
    public void update(Skill entity) {

    }

    @Override
    public void deleteById(Integer integer) {

    }
}



