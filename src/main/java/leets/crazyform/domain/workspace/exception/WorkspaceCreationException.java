package leets.crazyform.domain.workspace.exception;

import leets.crazyform.global.error.ErrorCode;
import leets.crazyform.global.error.exception.ServiceException;

public class WorkspaceCreationException extends ServiceException {
    public WorkspaceCreationException(String s, Exception e) {
        super(ErrorCode.WORKSPACE_NOT_CREATED);
    }
}