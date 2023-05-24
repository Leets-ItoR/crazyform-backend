package leets.crazyform.domain.workspace.domain;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import leets.crazyform.domain.shared.entity.BaseTimeEntity;
import leets.crazyform.domain.workspace.exception.WorkspaceNotFoundException;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "workspaces")
public class Workspace extends BaseTimeEntity {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String handle;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // 다른 필드들...

    @PreDestroy
    public void preDestroy() {
        // preDestroy() method implementation
    }

    @Transactional
    public JwtResponse execute(String name, String handle) {
        return null;
    }

    @Transactional
    public Workspace createWorkspace(String name, String handle) {
        this.createdAt = LocalDateTime.now();
        return null;
    }

    @Transactional
    public Workspace updateWorkspace(UUID workspaceId, String name, String handle) throws WorkspaceNotFoundException {
        this.updatedAt = LocalDateTime.now();
        return null;
    }

    @Transactional
    public void deleteWorkspace(UUID workspaceId) throws WorkspaceNotFoundException {

    }

    public Workspace getWorkspaceById(UUID workspaceId) throws WorkspaceNotFoundException {
        return null;
    }
}
