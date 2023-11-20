package com.moa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;


@EnableKafka
@EnableJpaAuditing
@SpringBootApplication
public class BeMeetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeMeetingApplication.class, args);
	}

}
