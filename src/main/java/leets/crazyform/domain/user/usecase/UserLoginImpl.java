package leets.crazyform.domain.user.usecase;

import leets.crazyform.domain.user.exception.UserNotFoundException;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserLoginImpl implements UserLogin {
    @Override
    public JwtResponse execute(String email, String password) throws UserNotFoundException {
        // TODO: 여기서 실제 코드를 구현합니다.
        return new JwtResponse("", "");
    }
}
