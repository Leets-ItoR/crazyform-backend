package leets.crazyform.domain.workspace.exception;

import leets.crazyform.global.error.ErrorCode;
import leets.crazyform.global.error.exception.ServiceException;

public class WorkspaceDeletionException extends ServiceException {
    public WorkspaceDeletionException() {
        super(ErrorCode.WORKSPACE_NOT_DELETED);
    }
}
