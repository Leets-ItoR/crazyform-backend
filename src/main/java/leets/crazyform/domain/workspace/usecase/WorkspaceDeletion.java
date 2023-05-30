package leets.crazyform.domain.workspace.usecase;

import java.util.UUID;

public interface WorkspaceDeletion {
    void deleteWorkspace(UUID workspaceId);
}