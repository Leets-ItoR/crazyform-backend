package leets.crazyform.domain.user.presentation;


import leets.crazyform.domain.user.usecase.UserLogin;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserLogin userLogin;

    @GetMapping("/login")
    public JwtResponse login() {
        // TODO: 아래와 같은 형태로 usecase를 호출합니다.
        return userLogin.execute("email", "password");
    }
}