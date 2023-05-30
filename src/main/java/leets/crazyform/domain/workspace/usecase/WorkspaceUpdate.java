package leets.crazyform.domain.workspace.usecase;

import leets.crazyform.domain.workspace.domain.Workspace;
import leets.crazyform.domain.workspace.exception.WorkspaceNotFoundException;

import java.util.UUID;

public interface WorkspaceUpdate {
    Workspace updateWorkspace(UUID workspaceId, String name, String handle) throws WorkspaceNotFoundException;
}
