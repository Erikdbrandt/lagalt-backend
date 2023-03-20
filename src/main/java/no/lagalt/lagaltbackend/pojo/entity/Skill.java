package no.lagalt.lagaltbackend.pojo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int skill_id;
    private String name;
    private String description;

    @ManyToMany
    @JoinTable(name = "user_skill",
            joinColumns = @JoinColumn(name = "skill_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<AppUser> users;
    @ManyToMany(mappedBy = "skills")
    private Set<Project> projects;

}
