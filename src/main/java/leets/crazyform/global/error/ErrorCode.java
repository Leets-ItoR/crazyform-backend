package leets.crazyform.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다."),
    INVALID_REQUEST(400, "INVALID_REQUEST", "요청이 올바르지 않습니다."),
    COOKIE_NOT_FOUND(400, "COOKIE_NOT_FOUND", "쿠키를 찾을 수 없습니다."),
    INVALID_TOKEN(401, "INVALID_TOKEN", "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(403, "EXPIRED_TOKEN", "만료된 토큰입니다."),
    USER_NOT_FOUND(404, "USER_NOT_FOUND", "존재하지 않는 설문참여자입니다."),
    CREATOR_NOT_FOUND(404, "CREATOR_NOT_FOUND", "존재하지 않는 설문제작자입니다."),
    PASSWORD_NOT_MATCH(400, "PASSWORD_NOT_MATCH", "비밀번호가 일치하지 않습니다."),
    EMAIL_DUPLICATE(409, "EMAIL_DUPLICATE", "중복된 이메일입니다."),
    WORKSPACE_NOT_FOUND(404, "WORKSPACE_NOT_FOUND", "워크스페이스를 찾을 수 없습니다."),
    ONLY_SOCIAL_LOGIN(400, "ONLY_SOCIAL_LOGIN", "소셜로그인으로 가입한 계정입니다.");

    private final int httpStatus;
    private final String code;
    private final String message;
}
