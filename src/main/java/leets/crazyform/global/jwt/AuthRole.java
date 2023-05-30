package leets.crazyform.global.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AuthRole {
    ROLE_CREATOR("ROLE_CREATOR"),
    ROLE_USER("ROLE_USER");

    private final String role;
}
