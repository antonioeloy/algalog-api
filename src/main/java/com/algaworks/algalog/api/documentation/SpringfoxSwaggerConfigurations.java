package com.algaworks.algalog.api.documentation;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringfoxSwaggerConfigurations {
	
	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))              
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(apiInfo());
    }
	
	private ApiInfo apiInfo() {
	    return new ApiInfo(
	      "algalog-api", 
	      "API REST com Spring Boot para um sistema de entrega de mercadorias.", 
	      "Versão 1.0", 
	      "https://github.com/antonioeloy/algalog-api/blob/master/README.md", 
	      new Contact("Antonio Eloy", "https://github.com/antonioeloy", "antonioeloy14@gmail.com"), 
	      "MIT License", "https://github.com/antonioeloy/algalog-api/blob/master/LICENSE", Collections.emptyList());
	}

}
