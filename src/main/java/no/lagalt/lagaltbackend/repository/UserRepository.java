package no.lagalt.lagaltbackend.repository;

import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser,Integer> {
    @Query("SELECT e FROM AppUser e WHERE e.email=?1")
    Optional<AppUser> findAppUserByEmail(String name);

    AppUser findByEmail(String email);
}
