package leets.crazyform.domain.user.usecase;

import leets.crazyform.global.jwt.AuthRole;
import leets.crazyform.global.jwt.dto.JwtResponse;

public interface RefreshToken {
    JwtResponse execute(String refreshToken);
}
