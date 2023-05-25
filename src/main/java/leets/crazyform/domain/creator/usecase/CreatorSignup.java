package leets.crazyform.domain.creator.usecase;

import leets.crazyform.global.jwt.dto.JwtResponse;

public interface CreatorSignup {
    JwtResponse execute(String email, String password, String nickname);
}
