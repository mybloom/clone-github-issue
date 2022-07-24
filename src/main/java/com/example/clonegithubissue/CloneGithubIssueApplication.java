package com.example.clonegithubissue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CloneGithubIssueApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloneGithubIssueApplication.class, args);
	}

}
