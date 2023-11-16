package com.tujuhsembilan.template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.tujuhsembilan.template", "com.tujuhsembilan.library" })
public class TemplateApplication {

	public static void main(String[] args) {

		SpringApplication.run(TemplateApplication.class, args);
	}

}
