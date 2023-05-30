package leets.crazyform.domain.workspace.usecase;

import leets.crazyform.domain.workspace.domain.Workspace;
import leets.crazyform.domain.workspace.exception.WorkspaceNotFoundException;

import java.util.UUID;

public interface WorkspaceRetrieval {
    Workspace getWorkspaceById(UUID workspaceId) throws WorkspaceNotFoundException;
}
