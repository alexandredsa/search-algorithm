import br.com.searchalgorithm.engine.SearchExecutor;
import br.com.searchalgorithm.file.FileReader;

import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class Main {
    private static final int BATCH_SIZE = 300;

    public static void main(String[] args) {
        Instant start = Instant.now();

        List<Path> filenames = FileReader.getFilenames("/Users/alexandre/zProjects/lulabs/data");
        List<String> result = SearchExecutor.with(filenames, args, BATCH_SIZE).run();
        displayResult(result, args);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
        System.exit(0);
    }

    private static void displayResult(List<String> result, String[] terms) {
        System.out.println(String.format("Foram encontradas %d ocorrências pelo termo %s",
                result.size(), String.join(" ", terms)));

        System.out.println(String.format("Os arquivos que possuem \"%s\" são:", String.join(" ", terms)));
        result.forEach(System.out::println);
    }
}
