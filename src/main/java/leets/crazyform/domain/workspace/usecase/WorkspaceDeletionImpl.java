package leets.crazyform.domain.workspace.usecase;

import leets.crazyform.domain.workspace.exception.WorkspaceNotFoundException;
import leets.crazyform.domain.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkspaceDeletionImpl implements WorkspaceDeletion {
    private final WorkspaceRepository workspaceRepository;

    @Transactional
    @Override
    public void deleteWorkspace(UUID workspaceId) {
        if (!workspaceRepository.existsById(workspaceId)) {
            throw new WorkspaceNotFoundException();
        }

        workspaceRepository.deleteById(workspaceId);

    }
}
