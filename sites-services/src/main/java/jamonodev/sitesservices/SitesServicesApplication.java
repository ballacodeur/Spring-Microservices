package jamonodev.sitesservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SitesServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SitesServicesApplication.class, args);
	}

}
