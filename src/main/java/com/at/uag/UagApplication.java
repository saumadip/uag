package com.at.uag;

import com.at.uag.api.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.logging.Logger;

@SpringBootApplication
@ComponentScan({"com.at.uag"})
public class UagApplication {

	private static Logger log = Logger.getLogger(UagApplication.class.getName());

	public static void main(String[] args)
	{
		ConfigurableApplicationContext applicationContext = SpringApplication.run(UagApplication.class, args);

		for (String name : applicationContext.getBeanDefinitionNames()) {
			log.info(name);
		}
	}

}
