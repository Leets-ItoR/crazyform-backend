package leets.crazyform.domain.workspace.usecase;

import leets.crazyform.domain.workspace.domain.Workspace;
import leets.crazyform.domain.workspace.exception.WorkspaceNotFoundException;
import leets.crazyform.domain.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkspaceUpdateImpl implements WorkspaceUpdate {
    private final WorkspaceRepository workspaceRepository;

    @Override
    public Workspace updateWorkspace(UUID workspaceId, String name, String handle) throws WorkspaceNotFoundException {
        Workspace workspace = workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new WorkspaceNotFoundException());

        workspace.setName(name);
        workspace.setHandle(handle);

        return workspaceRepository.save(workspace);
    }
}
