package no.lagalt.lagaltbackend.pojo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import no.lagalt.lagaltbackend.pojo.enums.ProjectStatusType;
import no.lagalt.lagaltbackend.pojo.enums.ProjectType;

import java.util.List;
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

    @Enumerated(EnumType.STRING)
    private ProjectStatusType project_status;

/*    @Enumerated(EnumType.STRING)
    private ProjectType project_type;*/



    private String project_type;

 /*   @ElementCollection
    @CollectionTable(name = "project_skills", joinColumns = @JoinColumn(name = "project_id"))
    @Column(name = "skill")
    private Set<String> wantedSkills;*/

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
