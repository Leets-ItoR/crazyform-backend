package leets.crazyform.global.oauth;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import leets.crazyform.global.jwt.AuthRole;
import leets.crazyform.global.jwt.JwtProvider;
import leets.crazyform.global.jwt.detail.OAuthDetails;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
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
        String accessToken = jwtProvider.generateToken(email, AuthRole.ROLE_PARTICIPANT, false);
        String refreshToken = jwtProvider.generateToken(email, AuthRole.ROLE_PARTICIPANT, true);

        Map<String, Object> result = new HashMap<>();
        result.put("result", new JwtResponse(accessToken, refreshToken));

        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        jsonConverter.write(result, MediaType.APPLICATION_JSON, new ServletServerHttpResponse(response));
    }
}
