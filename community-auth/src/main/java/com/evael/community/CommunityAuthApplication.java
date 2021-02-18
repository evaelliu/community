package com.evael.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.evael.community",
		"com.evael.community",
})
public class CommunityAuthApplication {
	public static void main(String[] args) {
		SpringApplication.run(CommunityAuthApplication.class, args);
	}
}
