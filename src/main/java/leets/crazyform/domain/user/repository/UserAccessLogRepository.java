package leets.crazyform.domain.user.repository;

import leets.crazyform.domain.user.domain.UserAccessLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserAccessLogRepository extends JpaRepository<UserAccessLog, Long> {
    Optional<List<UserAccessLog>> findAllByUser_Id(UUID userId);
}
