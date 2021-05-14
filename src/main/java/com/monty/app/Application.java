package com.monty.app;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;


import com.sendgrid.SendGrid;

@SpringBootApplication(scanBasePackages = {"com.monty.services","com.monty.app"})
@EnableScheduling
public class Application {
	
	//private static final Logger LOGGER=LoggerFactory.getLogger(Application.class);
	
	@Value("${sendgrid.key}")
	String sendGridKey;
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
		return builder.build();
	} 
	
	
	@Bean
	public SendGrid getSendGrid() {
		SendGrid sg=new SendGrid(sendGridKey);
		return sg;
	}
	
	
	
	
	

}
