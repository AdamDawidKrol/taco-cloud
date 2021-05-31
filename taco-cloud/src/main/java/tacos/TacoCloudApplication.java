package tacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import tacos.web.DesignTacoController;

@SpringBootApplication
@ComponentScan(basePackageClasses=DesignTacoController.class)
@ComponentScan("com.tacos.data")
public class TacoCloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudApplication.class, args);
	}

}
