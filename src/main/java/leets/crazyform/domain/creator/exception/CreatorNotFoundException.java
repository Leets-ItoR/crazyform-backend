package leets.crazyform.domain.creator.exception;

import leets.crazyform.global.error.ErrorCode;
import leets.crazyform.global.error.exception.ServiceException;

public class CreatorNotFoundException extends ServiceException {
    public CreatorNotFoundException() {
        super(ErrorCode.CREATOR_NOT_FOUND);
    }
}
