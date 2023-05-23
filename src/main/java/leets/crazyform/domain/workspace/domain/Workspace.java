package leets.crazyform.domain.workspace.domain;

import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import leets.crazyform.domain.shared.entity.BaseTimeEntity;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "workspaces")
@SQLDelete(sql = "UPDATE workspace SET deleted_at=now() where id=?")
@Where(clause = "deleted_at IS NULL")
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

    @Column()
    private LocalDateTime deletedAt;

    @PreDestroy()
    public void preDestroy() {
        this.deletedAt = LocalDateTime.now();
    }
}

