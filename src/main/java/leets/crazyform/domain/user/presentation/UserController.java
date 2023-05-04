package leets.crazyform.domain.user.presentation;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import leets.crazyform.domain.user.presentation.dto.LoginRequest;
import leets.crazyform.domain.user.usecase.RefreshToken;
import leets.crazyform.domain.user.usecase.UserLogin;
import leets.crazyform.global.jwt.AuthRole;
import leets.crazyform.global.jwt.detail.AuthDetails;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserLogin userLogin;
    private final RefreshToken doRefreshToken;

    @PostMapping("/login")
    public JwtResponse login(HttpServletResponse res, @Validated @RequestBody LoginRequest loginRequest) {
        JwtResponse jwt = userLogin.execute(loginRequest.getEmail(), loginRequest.getPassword());
        Cookie cookie = new Cookie("refreshToken", jwt.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        res.addCookie(cookie);
        return jwt;
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@CookieValue("refreshToken") String refreshToken) {
        return doRefreshToken.execute(refreshToken);
    }
}
