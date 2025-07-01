package br.com.raposarts.xtreamingbox;

import br.com.raposarts.xtreamingbox.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XtreamingboxApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(XtreamingboxApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibirMenu();

	}
}
