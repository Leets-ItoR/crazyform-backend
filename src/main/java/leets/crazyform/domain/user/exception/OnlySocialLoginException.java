package leets.crazyform.domain.user.exception;

import leets.crazyform.global.error.ErrorCode;
import leets.crazyform.global.error.exception.ServiceException;

public class OnlySocialLoginException extends ServiceException {
    public OnlySocialLoginException() {
        super(ErrorCode.ONLY_SOCIAL_LOGIN);
    }
}
