package no.lagalt.lagaltbackend.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.lagalt.lagaltbackend.pojo.enums.UserVisibility;

import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String full_name;
    private String email;
    private String password;
    private String roles;
    @Enumerated(EnumType.STRING)
    private UserVisibility userVisibility;
    @OneToMany(mappedBy = "owner")
    private Set<Project> projects;

    @ManyToMany(mappedBy = "users")
    private Set<Skill> skills;


//    @JsonIgnore
//    private byte[] encryptedPassword;

}
