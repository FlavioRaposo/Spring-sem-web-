package br.com.raposarts.xtreamingbox.Model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosSeries(@JsonAlias("Title") String Titulo,
                          @JsonAlias("Year") Integer totalTemporadas,
                          @JsonAlias("imdbRating") String avaliacao) {
}
