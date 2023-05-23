package leets.crazyform.domain.workspace.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkspaceResponse {
    private UUID id;
    private String name;
    private String handle;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 다른 필드들...

    // 생성자, getter/setter, equals/hashCode, toString 등의 메서드 추가...
}
