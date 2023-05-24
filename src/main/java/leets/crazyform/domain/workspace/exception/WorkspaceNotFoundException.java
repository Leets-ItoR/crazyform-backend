package leets.crazyform.domain.workspace.exception;

import leets.crazyform.global.error.ErrorCode;
import leets.crazyform.global.error.exception.ServiceException;

public class WorkspaceNotFoundException extends ServiceException {
    public WorkspaceNotFoundException(String s) {
        super(ErrorCode.WORKSPACE_NOT_FOUND);
    }
}
