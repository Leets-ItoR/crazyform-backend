package leets.crazyform.domain.user.usecase;

import leets.crazyform.domain.user.domain.User;
import leets.crazyform.domain.user.exception.UserNotFoundException;
import leets.crazyform.domain.user.repository.UserRepository;
import leets.crazyform.global.jwt.AuthRole;
import leets.crazyform.global.jwt.JwtProvider;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserLoginImpl implements UserLogin {

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Override
    public JwtResponse execute(String email, String password) throws UserNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new UserNotFoundException();
        }

        String accessToken = jwtProvider.generateToken(user.getEmail(), AuthRole.USER.getRole(), false);
        String refreshToken = jwtProvider.generateToken(user.getEmail(), AuthRole.USER.getRole(), true);
        return new JwtResponse(accessToken, refreshToken);
    }
}
