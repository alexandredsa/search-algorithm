package br.com.searchalgorithm.index;


import br.com.searchalgorithm.index.engine.SearchExecutor;

import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.TreeSet;

public class Search {
    private static final String PARAM_CONTEXT_PATH = "path";
    private static final String PARAM_TERMS = "terms";

    public static void main(String[] args) {
        Instant start = Instant.now();
        final String path = System.getProperty(PARAM_CONTEXT_PATH, Paths.get(".").toAbsolutePath().normalize().toString());
        final String terms = System.getProperty(PARAM_TERMS, "");
        TreeSet<String> result = SearchExecutor.with("/home/oem/Documentos/movies", new String[]{"walt", "disney"}).run();
        displayResult(result, args);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
        System.exit(0);
    }

    private static void displayResult(TreeSet<String> result, String[] terms) {
        System.out.println(String.format("Foram encontradas %d ocorrências pelo termo %s",
                result.size(), String.join(" ", terms)));

        System.out.println(String.format("Os arquivos que possuem \"%s\" são:", String.join(" ", terms)));
        result.forEach(System.out::println);
    }
}
