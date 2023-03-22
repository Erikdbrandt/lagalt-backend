package no.lagalt.lagaltbackend.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import no.lagalt.lagaltbackend.pojo.enums.AuthorityType;
import no.lagalt.lagaltbackend.pojo.enums.UserVisibility;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String full_name;
    private String email;
    private String authority_type;
    @Enumerated(EnumType.STRING)
    private UserVisibility userVisibility;
    @OneToMany(mappedBy = "owner")
    private Set<Project> projects;

    @ManyToMany(mappedBy = "users")
    private Set<Skill> skills;


}
