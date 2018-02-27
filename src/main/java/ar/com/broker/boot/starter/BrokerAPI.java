package ar.com.broker.boot.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Class
 * 
 * @author martinvacas
 *
 */
@SpringBootApplication(scanBasePackages = "ar.com.broker")
public class BrokerAPI {

	public static void main(String[] args) {
		SpringApplication.run(BrokerAPI.class, args);

	}

}
