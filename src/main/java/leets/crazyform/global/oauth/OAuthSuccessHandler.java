package leets.crazyform.global.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import leets.crazyform.global.jwt.AuthRole;
import leets.crazyform.global.jwt.JwtProvider;
import leets.crazyform.global.jwt.detail.OAuthDetails;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {
    private final JwtProvider jwtProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuthDetails oAuthDetails = (OAuthDetails) authentication.getPrincipal();
        String email = oAuthDetails.getEmail();
        String accessToken = jwtProvider.generateToken(email, AuthRole.ROLE_USER, false);
        String refreshToken = jwtProvider.generateToken(email, AuthRole.ROLE_USER, true);

        Cookie cookie = new Cookie("refreshToken", refreshToken);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        response.addCookie(cookie);

        Map<String, Object> result = new HashMap<>();
        result.put("result", new JwtResponse(accessToken, refreshToken));

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(result));
    }
}
