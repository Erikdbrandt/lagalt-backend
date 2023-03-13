package no.lagalt.lagaltbackend.repository;

import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByEmail(String username);
}
