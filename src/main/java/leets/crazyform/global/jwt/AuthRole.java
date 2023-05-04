package leets.crazyform.global.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthRole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_PARTICIPANT("ROLE_PARTICIPANT");

    private final String role;
}
