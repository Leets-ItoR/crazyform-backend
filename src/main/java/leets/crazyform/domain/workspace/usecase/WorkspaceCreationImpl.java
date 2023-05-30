package leets.crazyform.domain.workspace.usecase;

import leets.crazyform.domain.workspace.domain.Workspace;
import leets.crazyform.domain.workspace.repository.WorkspaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WorkspaceCreationImpl implements WorkspaceCreation {
    private final WorkspaceRepository workspaceRepository;

    @Transactional
    @Override
    public Workspace createWorkspace(String name, String handle) {
        LocalDateTime currentTime = LocalDateTime.now();
        Workspace workspace = Workspace.builder()
                .name(name)
                .handle(handle)
                .build();

        return workspaceRepository.save(workspace);

    }
}
