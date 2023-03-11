package no.lagalt.lagaltbackend.repository;

import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<AppUser,Integer> {
    @Query("SELECT e FROM AppUser e WHERE e.email=?1")
    Optional<AppUser> findAppUserByEmail(String email);

    boolean existsByEmail(String email);
}
