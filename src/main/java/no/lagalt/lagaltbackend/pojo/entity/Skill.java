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
    private String name;// Enum may be?
    private String description;
    @ManyToMany
    private Set<AppUser> users;
    @ManyToMany
    private Set<Project> projects;

}
