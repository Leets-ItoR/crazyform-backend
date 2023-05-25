package leets.crazyform.domain.auth.usecase;

import leets.crazyform.global.jwt.dto.JwtResponse;

public interface RefreshToken {
    JwtResponse execute(String refreshToken);
}
