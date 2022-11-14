package com.rohit.call;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
public class CallServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallServiceApplication.class, args);
	}

	@Bean
	public Docket docket() {
		ApiInfoBuilder builder=new ApiInfoBuilder();
		builder.title("Call-Service API documentation");
		builder.version("1.0");
		builder.description("This api is get callDetails and add Call Details");
		builder.license("com.rohit");
		builder.licenseUrl("http://rohitmisal.com");
		
	ApiInfo apiInfo=builder.build();
	return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.apis(RequestHandlerSelectors.basePackage("com.rohit.call.api"))
			.paths(PathSelectors.any())
			.build()
			.apiInfo(apiInfo);
	
	}
}
