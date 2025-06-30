package br.com.raposarts.xtreamingbox;

import br.com.raposarts.xtreamingbox.Model.DadosSeries;
import br.com.raposarts.xtreamingbox.Service.ConsumoAPI;
import br.com.raposarts.xtreamingbox.Service.ConverteDados;
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
		var consumoApi = new ConsumoAPI();
		var json = consumoApi.obterDados("https://www.omdbapi.com/?i=tt3896198&apikey=85bf8c54");
		System.out.println(json);
		ConverteDados conversor = new ConverteDados();
		DadosSeries dados = conversor.obterDados(json, DadosSeries.class);
		System.out.println(dados);
	}
}
