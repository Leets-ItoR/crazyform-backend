package leets.crazyform.domain.workspace.usecase;

import leets.crazyform.domain.workspace.domain.Workspace;

public interface WorkspaceCreation {
    Workspace createWorkspace(String name, String handle);
}
