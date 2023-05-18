package leets.crazyform.global.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
@EnableWebMvc
@Slf4j
public class CorsConfig implements WebMvcConfigurer {

    private String developmentOrigin;
    private String productionOrigin;

    public CorsConfig(@Value("${cors.host.development}") String developmentOrigin, @Value("${cors.host.production}") String productionOrigin) {
        this.developmentOrigin = developmentOrigin;
        this.productionOrigin = productionOrigin;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                .allowedOrigins("http://localhost:5173", "http://localhost:5174", "http://localhost:5175");
    }
}
