package no.lagalt.lagaltbackend.service;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.pojo.entity.Skill;
import no.lagalt.lagaltbackend.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {


    private final SkillRepository skillRepository;

    @Override
    public Collection<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findById(Integer id) {
        return skillRepository.findById(id).get();
    }

    @Override
    public Skill create(Skill entity) {
        return skillRepository.save(entity);
    }

    @Override
    public Skill update(Integer id, Skill entity) {

        Skill skill = findById(id);

        skill.setName(entity.getName());
        skill.setDescription(entity.getDescription());

        return skillRepository.save(skill);
    }

    @Override
    public void deleteById(Integer integer) {

        if (skillRepository.existsById(integer)) {
            Skill skill = findById(integer);
            if (skill.getProjects() != null) {
                skill.getProjects().forEach(project -> project.getSkills().remove(skill));
            }
            if (skill.getUsers() != null) {
                skill.getUsers().forEach(user -> user.getSkills().remove(skill));
            }

            skillRepository.deleteById(integer);

        }
    }
}



