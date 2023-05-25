package leets.crazyform.domain.creator.usecase;

import io.jsonwebtoken.Claims;
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
    public JwtResponse execute(String refreshToken) {
        jwtProvider.validateToken(refreshToken, true);
        Claims claims = jwtProvider.parseClaims(refreshToken, true);
        String role = claims.get("role", String.class);
        String newAccessToken = jwtProvider.generateToken(claims.getSubject(), AuthRole.valueOf(role), false);
        return new JwtResponse(newAccessToken, null);
    }
}
