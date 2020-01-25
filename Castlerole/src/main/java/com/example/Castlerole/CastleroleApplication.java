package com.example.Castlerole;

import com.example.Castlerole.service.ResourcesUpdatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class CastleroleApplication {

	@Autowired
	private ResourcesUpdatingService resourcesUpdatingService;

	public static void main(String[] args) {
		SpringApplication.run(CastleroleApplication.class, args);
	}

	// Update resources every minute
	@Scheduled(fixedRate = 60000L)
	void updateResources(){
		resourcesUpdatingService.updateResources();
	}

}

@Configuration
@EnableScheduling
@ConditionalOnProperty(name = "scheduling.enabled", matchIfMissing = true)
class SchedulingConfiguration {

}