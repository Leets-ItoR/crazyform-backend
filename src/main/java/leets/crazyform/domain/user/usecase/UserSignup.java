package leets.crazyform.domain.user.usecase;

import leets.crazyform.global.jwt.dto.JwtResponse;

public interface UserSignup {
    JwtResponse execute(String email, String password, String nickname);
}
