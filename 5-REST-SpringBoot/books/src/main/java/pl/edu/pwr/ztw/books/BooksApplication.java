package pl.edu.pwr.ztw.books;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.Collections;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class BooksApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(BooksApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
		app.run(args);
	}
}
