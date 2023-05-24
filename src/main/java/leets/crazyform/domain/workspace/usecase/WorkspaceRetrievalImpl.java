package leets.crazyform.domain.workspace.usecase;

import leets.crazyform.domain.workspace.domain.Workspace;
import leets.crazyform.domain.workspace.exception.WorkspaceNotFoundException;
import leets.crazyform.domain.workspace.repository.WorkspaceRepository;

import java.util.UUID;

public class WorkspaceRetrievalImpl implements WorkspaceRetrieval {
    private final WorkspaceRepository workspaceRepository;

    public WorkspaceRetrievalImpl(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    @Override
    public Workspace getWorkspaceById(UUID workspaceId) throws WorkspaceNotFoundException {
        return workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found with ID: " + workspaceId));
    }
}
