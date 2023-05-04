package leets.crazyform.domain.user.usecase;

import leets.crazyform.domain.user.domain.User;
import leets.crazyform.domain.user.repository.UserRepository;
import leets.crazyform.domain.user.exception.EmailDuplicateException;
import leets.crazyform.domain.user.exception.PasswordInvalidException;
import leets.crazyform.domain.user.type.Vendor;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserSignupImpl implements UserSignup {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public JwtResponse execute(String email, String password, String nickname) throws EmailDuplicateException, PasswordInvalidException {
        // 이메일 중복 체크
        if (userRepository.findByEmail(email).isPresent()) {
            throw new EmailDuplicateException();
        }

        // 패스워드 유효성 체크
        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$")) {
            throw new PasswordInvalidException();
        }

        // User 객체 생성
        User user = User.builder()
                .email(email)
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .build();

        // User 객체 저장
        userRepository.save(user);

        return null;
    }
    // JWT 토큰 생성 메소드
    private String generateJwtToken(UUID userId, String email, String nickname, Vendor vendor) {
        // TODO: JWT 토큰 생성 로직 구현
        return "";
    }
}
