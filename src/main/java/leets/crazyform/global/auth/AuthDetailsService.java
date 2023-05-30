package leets.crazyform.global.auth;

import leets.crazyform.domain.creator.domain.Creator;
import leets.crazyform.domain.creator.repository.CreatorRepository;
import leets.crazyform.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthDetailsService implements UserDetailsService {
    private final CreatorRepository creatorRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Creator creator = this.creatorRepository
                .findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorCode.CREATOR_NOT_FOUND.getMessage()));
        return new AuthDetails(creator.getEmail());
    }
}
