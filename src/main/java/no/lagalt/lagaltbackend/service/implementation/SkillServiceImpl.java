package no.lagalt.lagaltbackend.service.implementation;

import lombok.RequiredArgsConstructor;
import no.lagalt.lagaltbackend.pojo.entity.Skill;
import no.lagalt.lagaltbackend.repository.SkillRepository;
import no.lagalt.lagaltbackend.service.services.SkillService;
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

        if(skillRepository.existsById(integer)){
            Skill skill = findById(integer);
            if(skill.getProjects() != null){
                skill.getProjects().forEach(project -> project.getSkills().remove(skill));
            }
            if(skill.getUsers() != null){
                skill.getUsers().forEach(user -> user.getSkills().remove(skill));
            }

            skillRepository.deleteById(integer);

        }
    }

    @Override
    public List<String> findAllSkillNames() {
        List<Skill> allSkills = skillRepository.findAll();
        List<String> allSkillNames = allSkills.stream().map(Skill ::getName).collect(Collectors.toList());
        return allSkillNames;
    }
}



