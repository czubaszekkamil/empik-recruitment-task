package com.empik.githubadapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GithubAdapterApplication {

	public static void main(String[] args) {
		SpringApplication.run(GithubAdapterApplication.class, args);
	}

}
