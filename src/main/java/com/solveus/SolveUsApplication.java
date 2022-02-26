package com.solveus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableJpaAuditing
@SpringBootApplication
@EntityScan("com.solveus.domain.entity")
@PropertySource(value={"classpath:config.properties"})
public class SolveUsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SolveUsApplication.class, args);
    }

}
