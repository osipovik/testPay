package oik.test_pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableJpaRepositories("oik.test_pay.repository")
@EnableAsync
public class TestPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestPayApplication.class, args);
    }
}
