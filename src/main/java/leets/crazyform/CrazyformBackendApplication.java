package leets.crazyform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CrazyformBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrazyformBackendApplication.class, args);
    }

}
