package leets.crazyform.global.security;

import leets.crazyform.global.filter.ExceptionHandleFilter;
import leets.crazyform.global.jwt.JwtFilter;
import leets.crazyform.global.jwt.JwtProvider;
import leets.crazyform.global.jwt.detail.OAuthDetailService;
import leets.crazyform.global.oauth.OAuthSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    private final JwtProvider jwtProvider;
    private final OAuthDetailService oAuthDetailService;
    private final OAuthSuccessHandler oAuthSuccessHandler;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .cors().and()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers(CorsUtils::isCorsRequest).permitAll()

                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()

                .requestMatchers("/user/login").permitAll()
                .requestMatchers("/user/refresh").permitAll()
                .requestMatchers("/user/signup").permitAll()

                .anyRequest().authenticated()
                .and()

                .oauth2Login()
                .successHandler(oAuthSuccessHandler)
                .redirectionEndpoint()
                .baseUri("/oauth/callback/**")
                .and()
                .authorizationEndpoint()
                .baseUri("/oauth/login")
                .and()
                .userInfoEndpoint()
                .userService(oAuthDetailService)
                .and().and()

                .addFilterBefore(new JwtFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new ExceptionHandleFilter(), JwtFilter.class)
                .build();
    }
}