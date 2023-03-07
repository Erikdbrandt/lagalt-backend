package no.lagalt.lagaltbackend.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import no.lagalt.lagaltbackend.pojo.enums.ProjectStatusType;
import no.lagalt.lagaltbackend.pojo.enums.ProjectType;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int project_id;
    private String title;
    private String description;
    private String theme;
    private ProjectStatusType project_status;
    private ProjectType project_type;
    @ManyToOne
    @JoinTable(name = "project_owner",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private AppUser owner;
    @ManyToMany
    @JoinTable(name = "project_participant",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<AppUser> participants;
    @ManyToMany(mappedBy = "projects")
    private Set<Skill> skills;

}
