package no.lagalt.lagaltbackend.repository;

import no.lagalt.lagaltbackend.pojo.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
