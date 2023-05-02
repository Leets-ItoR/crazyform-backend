package leets.crazyform.domain.user.usecase;

import leets.crazyform.global.jwt.dto.JwtResponse;

public interface UserLogin {
    JwtResponse execute(String email, String password);
}
