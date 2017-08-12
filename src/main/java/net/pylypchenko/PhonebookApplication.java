package net.pylypchenko;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class PhonebookApplication {

	public static void main(String[] args) {
		String prop = System.getProperty("lardi.conf");
		SpringApplicationBuilder builder = new SpringApplicationBuilder(PhonebookApplication.class);
		builder.properties("spring.config.location=" + prop).run(args);
	}

}
