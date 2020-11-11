package com.cg.fms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket FeedbackSystemConfigApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo()); 
	}
	
	@Bean
	public Docket FeedbackSystemConfigTrainerApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Trainer").select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/trainer/**"))
				.build()
				.apiInfo(getApiInfo()); 
	}
	
	@Bean
	public Docket FeedbackSystemConfigProgramApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Program").select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/program/**"))
				.build()
				.apiInfo(getApiInfo()); 
	}
	
	@Bean
	public Docket FeedbackSystemConfigFeedbackApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Feedback").select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/feedback/**"))
				.build()
				.apiInfo(getApiInfo()); 
	}
	
	@Bean
	public Docket FeedbackSystemConfigCourseApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Course").select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/course/**"))
				.build()
				.apiInfo(getApiInfo()); 
	}
	
	@Bean
	public Docket FeedbackSystemConfigParticipantApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Participant").select()
				.apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.ant("/participant/**"))
				.build()
				.apiInfo(getApiInfo()); 
	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
				.title("Feedback Management System API")
				.version("1.0")
				.description("API for managing the feedbacks of training programs.")
				.contact(new Contact("Rohit", "https://github.com/ROHITCHANDRABHATT/Project.git", "fms@gmail.com"))
				.build();
	}
	
}
