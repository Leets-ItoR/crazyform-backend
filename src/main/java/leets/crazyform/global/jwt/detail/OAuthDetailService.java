package leets.crazyform.global.jwt.detail;

import leets.crazyform.domain.user.domain.User;
import leets.crazyform.domain.user.repository.UserRepository;
import leets.crazyform.domain.user.type.Vendor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class OAuthDetailService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String vendor = userRequest.getClientRegistration().getRegistrationId();
        Map<String, Object> oAuthInfo = oAuth2User.getAttributes();

        if (vendor.equalsIgnoreCase(Vendor.NAVER.getVendor())) {
            oAuthInfo = (Map<String, Object>) oAuth2User.getAttributes().get("response");
        }

        String email = oAuthInfo.get("email").toString();
        String name = oAuthInfo.get("name").toString();

        User user = userRepository.findByEmail(email).orElse(null);
        if (user == null) {
            User createdUser = User.builder()
                    .email(email)
                    .nickname(name)
                    .vendor(Vendor.valueOf(vendor))
                    .build();
            userRepository.save(createdUser);
        }

        return new OAuthDetails(email, name, oAuthInfo);
    }
}
