package leets.crazyform.domain.creator.usecase;

import leets.crazyform.domain.creator.domain.Creator;
import leets.crazyform.domain.creator.exception.CreatorNotFoundException;
import leets.crazyform.domain.creator.repository.CreatorRepository;
import leets.crazyform.domain.user.exception.PasswordNotMatchException;
import leets.crazyform.global.jwt.AuthRole;
import leets.crazyform.global.jwt.JwtProvider;
import leets.crazyform.global.jwt.dto.JwtResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatorLoginImpl implements CreatorLogin {

    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;
    private final CreatorRepository creatorRepository;

    @Override
    public JwtResponse execute(String email, String password) {
        Creator creator = creatorRepository.findByEmail(email)
                .orElseThrow(CreatorNotFoundException::new);

        if (!passwordEncoder.matches(password, creator.getPassword())) {
            throw new PasswordNotMatchException();
        }

        String accessToken = jwtProvider.generateToken(creator.getEmail(), AuthRole.ROLE_CREATOR, false);
        String refreshToken = jwtProvider.generateToken(creator.getEmail(), AuthRole.ROLE_CREATOR, true);
        return new JwtResponse(accessToken, refreshToken);
    }
}
