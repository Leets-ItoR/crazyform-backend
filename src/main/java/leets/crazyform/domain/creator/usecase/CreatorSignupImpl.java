package leets.crazyform.domain.creator.usecase;

import leets.crazyform.domain.creator.domain.Creator;
import leets.crazyform.domain.creator.repository.CreatorRepository;
import leets.crazyform.domain.user.exception.EmailDuplicateException;
import leets.crazyform.domain.user.exception.PasswordInvalidException;
import leets.crazyform.global.jwt.AuthRole;
import leets.crazyform.global.jwt.JwtProvider;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CreatorSignupImpl implements CreatorSignup {
    private final JwtProvider jwtProvider;
    private final CreatorRepository creatorRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public JwtResponse execute(String email, String password, String nickname) throws EmailDuplicateException, PasswordInvalidException {
        // 이메일 중복 체크
        if (creatorRepository.findByEmail(email).isPresent()) {
            throw new EmailDuplicateException();
        }

        // 패스워드 유효성 체크
//        if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{8,}$")) {
//            throw new PasswordInvalidException();
//        }

        // User 객체 생성
        Creator creator = Creator.builder()
                .email(email)
                .nickname(nickname)
                .password(passwordEncoder.encode(password))
                .build();

        // User 객체 저장
        creatorRepository.save(creator);

        String accessToken = jwtProvider.generateToken(creator.getEmail(), AuthRole.ROLE_CREATOR, false);
        String refreshToken = jwtProvider.generateToken(creator.getEmail(), AuthRole.ROLE_CREATOR, true);
        return new JwtResponse(accessToken, refreshToken);
    }
}
