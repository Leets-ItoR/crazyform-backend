package leets.crazyform.infrastructure.error.exception;

import leets.crazyform.infrastructure.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ServiceException extends RuntimeException {
    private final ErrorCode errorCode;
}