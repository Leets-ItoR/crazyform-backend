package leets.crazyform.domain.workspace.repository;

import leets.crazyform.domain.workspace.domain.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkspaceRepository extends JpaRepository<Workspace, UUID> {
    // 추가적인 메서드 선언 가능
}
