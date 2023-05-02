package leets.crazyform.global.jwt;

import io.jsonwebtoken.*;
import leets.crazyform.global.jwt.detail.AuthDetailsService;
import leets.crazyform.global.jwt.exception.ExpiredTokenException;
import leets.crazyform.global.jwt.exception.InvalidTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Component
public class JwtProvider {
    private final String secret;
    private final AuthDetailsService authDetailsService;

    @Autowired
    public JwtProvider(@Value("${jwt.secret}") String secret, AuthDetailsService authDetailsService) {
        this.secret = secret;
        this.authDetailsService = authDetailsService;
    }

    public String generateToken(String email, String role, boolean isRefreshToken) {
        Instant accessDate = LocalDateTime.now().plusHours(6).atZone(ZoneId.systemDefault()).toInstant();
        Instant refreshDate = LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant();
        return Jwts.builder()
                .claim("role", role)
                .setSubject(email)
                .setExpiration(isRefreshToken ? Date.from(refreshDate) : Date.from(accessDate))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = parseClaims(token);
        Collection<? extends GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(claims.get("role").toString()));
        UserDetails principal = this.authDetailsService.loadUserByUsername(claims.getSubject());
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (SignatureException | UnsupportedJwtException | IllegalArgumentException | MalformedJwtException e) {
            throw new InvalidTokenException();
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        }
    }

    private Claims parseClaims(String accessToken) {
        try {
            JwtParser parser = Jwts.parser().setSigningKey(secret);
            return parser.parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
