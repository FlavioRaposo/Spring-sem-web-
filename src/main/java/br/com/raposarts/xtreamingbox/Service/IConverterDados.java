package br.com.raposarts.xtreamingbox.Service;

public interface IConverterDados {
    <T> T obterDados(String json, Class<T> classe);
}
