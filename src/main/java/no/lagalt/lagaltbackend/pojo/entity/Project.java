package no.lagalt.lagaltbackend.pojo.entity;

import jakarta.persistence.*;
import lombok.*;
import no.lagalt.restapi.pojo.enums.ProjectStatusType;
import no.lagalt.restapi.pojo.enums.ProjectType;

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
    private ProjectStatusType status;
    private ProjectType projectType;
    @ManyToOne
    @NonNull
    private AppUser owner;
    @ManyToMany
    private Set<AppUser> participants;
    @ManyToMany
    private Set<Skill> skills;

}
