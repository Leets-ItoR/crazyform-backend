package leets.crazyform.app.user.repository;

import leets.crazyform.app.user.domain.UserAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserAccessLogRepository extends JpaRepository<UserAccessLog, Long> {
    Optional<List<UserAccessLog>> findAllByUserId(UUID userId);
}
