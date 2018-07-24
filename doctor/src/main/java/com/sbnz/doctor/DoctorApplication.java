package com.sbnz.doctor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = "com.sbnz.doctor")
@EntityScan(basePackages = "com.sbnz.doctor.model")
@EnableJpaRepositories("com.sbnz.doctor.repository")
@EnableTransactionManagement
@EnableConfigurationProperties
public class DoctorApplication {

	public static void main(String[] args) {
		SpringApplication.run(DoctorApplication.class, args);
	}
}
