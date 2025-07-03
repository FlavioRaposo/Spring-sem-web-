package br.com.raposarts.xtreamingbox.principal;

import br.com.raposarts.xtreamingbox.Model.DadosEpisodio;
import br.com.raposarts.xtreamingbox.Model.DadosSeries;
import br.com.raposarts.xtreamingbox.Model.DadosTemporada;
import br.com.raposarts.xtreamingbox.Service.ConsumoAPI;
import br.com.raposarts.xtreamingbox.Service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=85bf8c54&s";

    ConsumoAPI consumo = new ConsumoAPI();
    private ConverteDados conversor = new ConverteDados();

    public void exibirMenu() {
        System.out.println("Digite o nome da série para busca ");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + API_KEY);

        DadosSeries dadosSeries = conversor.obterDados(json, DadosSeries.class);
        System.out.println(dadosSeries);
        json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=1&episode=2" + API_KEY);
        DadosEpisodio dadosEpisodio = conversor.obterDados(json, DadosEpisodio.class);
        System.out.println(dadosEpisodio);

        List<DadosTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dadosSeries.totalTemporadas(); i++) {
            json = consumo.obterDados(ENDERECO + nomeSerie.replace(" ", "+") + "&season=" + i + API_KEY);
            DadosTemporada dadosTemporada = conversor.obterDados(json, DadosTemporada.class);
            temporadas.add(dadosTemporada);

        }
        temporadas.forEach(System.out::println);


        for (int i = 0; i < dadosSeries.totalTemporadas(); i++) {
            System.out.println("\nTemporada " + temporadas.get(i).numero());
            List<DadosEpisodio> episodiostemporada = temporadas.get(i).episodios();
             for(int j = 0; j< episodiostemporada.size();j++) {
                System.out.println(episodiostemporada.get(j).titulo());
            }
        }

        temporadas.forEach(t -> t.episodios().forEach(e -> e.titulo()));

        List<DadosEpisodio> dadosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList());

        System.out.println("\n Top 5 Episódios");
        dadosEpisodios.stream()
                .filter( e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed()) //ordenar do menor para o maior
                .limit(5)
                .forEach(System.out::println);


    }
}

