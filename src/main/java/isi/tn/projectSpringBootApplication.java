package isi.tn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories(basePackages="isi.tn")
@SpringBootApplication
@EnableScheduling
public class projectSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(projectSpringBootApplication.class, args);
	}

}
 