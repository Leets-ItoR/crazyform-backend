package leets.crazyform.domain.user.usecase;

import leets.crazyform.global.jwt.AuthRole;
import leets.crazyform.global.jwt.JwtProvider;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RefreshTokenImpl implements RefreshToken {
    private final JwtProvider jwtProvider;

    @Override
    public JwtResponse execute(String email, AuthRole role, String refreshToken) {
        jwtProvider.validateToken(refreshToken, true);
        String newAccessToken = jwtProvider.generateToken(email, role, false);
        return new JwtResponse(newAccessToken, null);
    }
}
