package leets.crazyform.domain.creator.repository;

import leets.crazyform.domain.creator.domain.Creator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CreatorRepository extends JpaRepository<Creator, UUID> {

    Optional<Creator> findByEmail(String email);

    boolean existsByEmail(String email);
}
