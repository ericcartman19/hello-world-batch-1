package io.valoyes.spring1.batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration/*(exclude={DataSourceAutoConfiguration.class})*/
public class HelloWorldBatch1Application {

	public static void main(String[] args) {
		SpringApplication.run(HelloWorldBatch1Application.class, args);
	}
}
