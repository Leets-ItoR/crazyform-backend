package leets.crazyform.domain.workspace.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import leets.crazyform.domain.workspace.presentation.dto.WorkspaceRequest;
import leets.crazyform.domain.workspace.presentation.dto.WorkspaceResponse;
import leets.crazyform.domain.workspace.usecase.WorkspaceCreation;
import leets.crazyform.domain.workspace.usecase.WorkspaceDeletion;
import leets.crazyform.domain.workspace.usecase.WorkspaceRetrieval;
import leets.crazyform.domain.workspace.usecase.WorkspaceUpdate;
import leets.crazyform.domain.workspace.exception.WorkspaceNotFoundException;
import leets.crazyform.domain.workspace.domain.Workspace;
import leets.crazyform.global.error.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/workspace")
public class WorkspaceController {
    private final WorkspaceCreation workspaceCreation;
    private final WorkspaceRetrieval workspaceRetrieval;
    private final WorkspaceUpdate workspaceUpdate;
    private final WorkspaceDeletion workspaceDeletion;

    @Operation(summary = "워크스페이스 생성", description = "워크스페이스를 생성합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = WorkspaceResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping("/create")
    public WorkspaceResponse createWorkspace(@Validated @RequestBody WorkspaceRequest workspaceRequest) {
        String name = workspaceRequest.getName();
        String handle = workspaceRequest.getHandle();
        Workspace workspace = workspaceCreation.createWorkspace(name, handle);
        return new WorkspaceResponse(workspace.getId(), workspace.getName(), workspace.getHandle(), workspace.getCreatedAt(), workspace.getUpdatedAt());
    }

    @Operation(summary = "워크스페이스 조회", description = "워크스페이스를 조회합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = WorkspaceResponse.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @GetMapping("/{workspaceId}")
    public WorkspaceResponse getWorkspace(@PathVariable UUID workspaceId) {
        try {
            Workspace workspace = workspaceRetrieval.getWorkspaceById(workspaceId);
            return new WorkspaceResponse(workspace.getId(), workspace.getName(), workspace.getHandle(), workspace.getCreatedAt(), workspace.getUpdatedAt());
        } catch (WorkspaceNotFoundException e) {
            // WorkspaceNotFoundException 처리
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorResponse.toString(), e);
        }
    }

    @Operation(summary = "워크스페이스 수정", description = "워크스페이스를 수정합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = WorkspaceResponse.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @PutMapping("/{workspaceId}")
    public WorkspaceResponse updateWorkspace(@PathVariable UUID workspaceId, @Validated @RequestBody WorkspaceRequest workspaceRequest) {
        String name = workspaceRequest.getName();
        String handle = workspaceRequest.getHandle();
        try {
            Workspace workspace = workspaceUpdate.updateWorkspace(workspaceId, name, handle);
            return new WorkspaceResponse(workspace.getId(), workspace.getName(), workspace.getHandle(), workspace.getCreatedAt(), workspace.getUpdatedAt());
        } catch (WorkspaceNotFoundException e) {
            // WorkspaceNotFoundException 처리
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorResponse.toString(), e);
        }
    }

    @Operation(summary = "워크스페이스 삭제", description = "워크스페이스를 삭제합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @DeleteMapping("/{workspaceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteWorkspace(@PathVariable UUID workspaceId) {
        try {
            workspaceDeletion.deleteWorkspace(workspaceId);
        } catch (WorkspaceNotFoundException e) {
            // WorkspaceNotFoundException 처리
            ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, errorResponse.toString(), e);
        }
    }
}
