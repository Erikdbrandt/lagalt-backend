package no.lagalt.lagaltbackend.repository;

import no.lagalt.lagaltbackend.pojo.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser,Integer> {
    AppUser findByEmail(String email);
}
