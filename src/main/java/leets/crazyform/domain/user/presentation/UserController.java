package leets.crazyform.domain.user.presentation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import leets.crazyform.domain.user.presentation.dto.LoginRequest;
import leets.crazyform.domain.user.presentation.dto.SignupRequest;
import leets.crazyform.domain.user.usecase.RefreshToken;
import leets.crazyform.domain.user.usecase.UserLogin;
import leets.crazyform.domain.user.usecase.UserSignup;
import leets.crazyform.global.error.ErrorResponse;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserLogin userLogin;
    private final UserSignup userSignup;
    private final RefreshToken doRefreshToken;

    @Operation(summary = "관리자(설문생성자) 로그인", description = "관리자 계정으로 로그인합니다. RefreshToken은 Cookie에 자동으로 추가됩니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = JwtResponse.class))),
            @ApiResponse(responseCode = "400", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @PostMapping("/login")
    public JwtResponse login(HttpServletResponse res, @Validated @RequestBody LoginRequest loginRequest) {
        JwtResponse jwt = userLogin.execute(loginRequest.getEmail(), loginRequest.getPassword());
        Cookie cookie = new Cookie("refreshToken", jwt.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        res.addCookie(cookie);
        return jwt;
    }

    @Operation(summary = "관리자(설문생성자) 토큰갱신", description = "AccessToken을 갱신합니다. 이 때 Cookie에 있는 RefreshToken을 자동으로 가져와 사용합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = JwtResponse.class))),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @PostMapping("/refresh")
    public JwtResponse refresh(@Parameter(hidden = true) @CookieValue("refreshToken") String refreshToken) {
        return doRefreshToken.execute(refreshToken);
    }

    @Operation(summary = "관리자(설문생성자) 회원가입", description = "관리자 회원가입을 합니다.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = JwtResponse.class))),
            @ApiResponse(responseCode = "409", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })

    @PostMapping("/signup")
    public JwtResponse signup(HttpServletResponse res, @Validated @RequestBody SignupRequest signupRequest) {
        JwtResponse jwt = userSignup.execute(signupRequest.getEmail(), signupRequest.getPassword(), signupRequest.getNickname());
        Cookie cookie = new Cookie("refreshToken", jwt.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        res.addCookie(cookie);
        return jwt;
    }
}
