package leets.crazyform.global.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@RequiredArgsConstructor
@Configuration
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
                .allowedOrigins(developmentOrigin, productionOrigin);
    }
}
