package leets.crazyform.domain.user.repository;

import leets.crazyform.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    User save(User user);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
