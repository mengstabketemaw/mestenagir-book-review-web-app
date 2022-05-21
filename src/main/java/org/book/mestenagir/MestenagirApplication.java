package org.book.mestenagir;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class MestenagirApplication {
	public static void main(String[] args) {
		SpringApplication.run(MestenagirApplication.class, args);
	}
}
