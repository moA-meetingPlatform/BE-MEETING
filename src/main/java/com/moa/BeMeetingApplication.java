package com.moa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.kafka.annotation.EnableKafka;


@EnableFeignClients
//@ImportAutoConfiguration({ FeignAutoConfiguration.class, HttpClientConfiguration.class })
@EnableKafka
@EnableJpaAuditing
@SpringBootApplication
public class BeMeetingApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeMeetingApplication.class, args);
	}

}
