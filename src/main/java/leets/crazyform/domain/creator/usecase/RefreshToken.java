package leets.crazyform.domain.creator.usecase;

import leets.crazyform.global.jwt.dto.JwtResponse;

public interface RefreshToken {
    JwtResponse execute(String refreshToken);
}
