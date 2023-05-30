package leets.crazyform.domain.workspace.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WorkspaceRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String handle;
}
