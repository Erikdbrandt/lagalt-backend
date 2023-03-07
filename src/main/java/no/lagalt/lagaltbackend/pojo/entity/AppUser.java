package no.lagalt.lagaltbackend.pojo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.lagalt.lagaltbackend.pojo.enums.AuthorityType;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String full_name;
    private String email;
    private String password;
    private AuthorityType authorityType;
    @OneToMany
    private Set<Project> projects;
    @ManyToMany
    private Set<Skill> skills;

}
