package leets.crazyform.domain.workspace.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class WorkspaceRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String handle;

//    @NotBlank
//    private LocalDateTime createdAt;
//
//    @NotBlank
//    private LocalDateTime updatedAt;

}
