package no.lagalt.lagaltbackend.pojo.entity;

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
    @NonNull
    private AppUser owner;
    @ManyToMany
    private Set<AppUser> participants;
    @ManyToMany
    private Set<Skill> skills;

}
