package com.sbnz.doctor;

import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.runtime.KieContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
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

	@Bean
	public KieContainer kieContainer() {
		KieServices ks = KieServices.Factory.get();
		KieContainer kContainer = ks
				.newKieContainer(ks.newReleaseId("com.sbnz", "drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
//				.newKieContainer(ks.newReleaseId("drools-spring-v2", "drools-spring-v2-kjar", "0.0.1-SNAPSHOT"));
		KieScanner kScanner = ks.newKieScanner(kContainer);

		kScanner.start(10_000);
		return kContainer;
	}
}
