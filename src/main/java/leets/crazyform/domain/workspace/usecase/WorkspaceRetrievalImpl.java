package leets.crazyform.domain.workspace.usecase;

import leets.crazyform.domain.workspace.domain.Workspace;
import leets.crazyform.domain.workspace.exception.WorkspaceNotFoundException;
import leets.crazyform.domain.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkspaceRetrievalImpl implements WorkspaceRetrieval {
    private final WorkspaceRepository workspaceRepository;

    @Override
    public Workspace getWorkspaceById(UUID workspaceId) throws WorkspaceNotFoundException {
        return workspaceRepository.findById(workspaceId)
                .orElseThrow(WorkspaceNotFoundException::new);
    }
}
