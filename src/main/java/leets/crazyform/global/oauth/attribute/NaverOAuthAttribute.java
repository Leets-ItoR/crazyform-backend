package leets.crazyform.global.oauth.attribute;

import java.util.Map;

public class NaverOAuthAttribute extends OAuthAttribute {
    public NaverOAuthAttribute(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getName() {
        return attributes.get("name").toString();
    }

    @Override
    public String getEmail() {
        return attributes.get("email").toString();
    }
}
