package pl.hgawrys.nasaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.hgawrys.nasaapi.controllers.MainController;
import sun.applet.Main;

@SpringBootApplication
public class NasaapiApplication {

	public static void main(String[] args) {

		SpringApplication.run(NasaapiApplication.class, args);
		MainController mainController = new MainController();
		mainController.run();
	}
}
