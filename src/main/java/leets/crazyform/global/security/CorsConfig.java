package leets.crazyform.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Value("${cors.host.development}")
    private String developmentOrigin;

    @Value("${cors.host.production}")
    private String productionOrigin;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("HEAD", "GET", "POST", "PUT", "PATCH", "DELETE")
                .allowCredentials(true)
                .allowedOrigins("http://localhost:5173",
                        "http://localhost:5174",
                        "http://localhost:5175",
                        developmentOrigin,
                        productionOrigin);
    }
}
