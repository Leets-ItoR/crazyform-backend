package leets.crazyform.domain.workspace.usecase;

import leets.crazyform.domain.workspace.domain.Workspace;
import leets.crazyform.domain.workspace.exception.WorkspaceNotFoundException;
import leets.crazyform.domain.workspace.repository.WorkspaceRepository;
import leets.crazyform.global.jwt.AuthRole;
import leets.crazyform.global.jwt.JwtProvider;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class WorkspaceImpl extends Workspace {
    private final WorkspaceRepository workspaceRepository;
    private final JwtProvider jwtProvider;

    @Transactional
    @Override
    public JwtResponse execute(String name, String handle) {
        LocalDateTime currentTime = LocalDateTime.now();
        Workspace workspace = Workspace.builder()
                .name(name)
                .handle(handle)
                .createdAt(currentTime)
                .updatedAt(currentTime)
                .build();

        workspaceRepository.save(workspace);

        String accessToken = jwtProvider.generateToken(workspace.getId().toString(), AuthRole.ROLE_ADMIN, false);
        String refreshToken = jwtProvider.generateToken(workspace.getId().toString(), AuthRole.ROLE_ADMIN, true);
        return new JwtResponse(accessToken, refreshToken);
    }

    @Transactional
    @Override
    public Workspace createWorkspace(String name, String handle) {
        LocalDateTime currentTime = LocalDateTime.now();
        Workspace workspace = Workspace.builder()
                .name(name)
                .handle(handle)
                .createdAt(currentTime)
                .updatedAt(currentTime)
                .build();
        return workspaceRepository.save(workspace);
    }

    @Transactional
    @Override
    public Workspace updateWorkspace(UUID workspaceId, String name, String handle) throws WorkspaceNotFoundException {
        Workspace workspace = getWorkspaceById(workspaceId);
        workspace.setName(name);
        workspace.setHandle(handle);
        workspace.setUpdatedAt(LocalDateTime.now());
        return workspaceRepository.save(workspace);
    }

    @Transactional
    @Override
    public void deleteWorkspace(UUID workspaceId) throws WorkspaceNotFoundException {
        Workspace workspace = getWorkspaceById(workspaceId);
        workspaceRepository.delete(workspace);
    }

    @Override
    public Workspace getWorkspaceById(UUID workspaceId) throws WorkspaceNotFoundException {
        return workspaceRepository.findById(workspaceId)
                .orElseThrow(() -> new WorkspaceNotFoundException("Workspace not found with ID: " + workspaceId));
    }
}
