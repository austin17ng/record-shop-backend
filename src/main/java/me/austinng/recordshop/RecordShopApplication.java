package me.austinng.recordshop;

import me.austinng.recordshop.model.User;
import me.austinng.recordshop.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class RecordShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordShopApplication.class, args);
	}
}
