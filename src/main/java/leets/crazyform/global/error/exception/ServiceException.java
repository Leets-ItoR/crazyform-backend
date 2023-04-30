package leets.crazyform.global.error.exception;

import leets.crazyform.global.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceException extends RuntimeException {
    private final ErrorCode errorCode;
}