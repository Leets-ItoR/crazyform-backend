package leets.crazyform.domain.workspace.repository;

import leets.crazyform.domain.workspace.domain.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WorkspaceRepository extends JpaRepository<Workspace, UUID> {
    // 추가적인 메서드 선언 가능
}
