package leets.crazyform.domain.user.presentation;


import leets.crazyform.domain.user.presentation.dto.LoginRequest;
import leets.crazyform.domain.user.usecase.UserLogin;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserLogin userLogin;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody LoginRequest loginRequest) {
        return userLogin.execute(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
