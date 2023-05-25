package leets.crazyform.domain.creator.usecase;

import leets.crazyform.global.jwt.dto.JwtResponse;

public interface CreatorLogin {
    JwtResponse execute(String email, String password);
}
