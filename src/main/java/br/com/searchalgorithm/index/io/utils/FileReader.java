package br.com.searchalgorithm.index.io.utils;

import br.com.searchalgorithm.index.models.IndexModel;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {
    public static List<Path> getFilenames(String path) {
        List<Path> filenames = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            filenames = paths.filter(Files::isRegularFile).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filenames;
    }

    public static TreeSet<String> wordsFromFile(Path path) throws FileNotFoundException {
        final Scanner scanner = new Scanner(path.toFile());
        TreeSet<String> words = new TreeSet<>();
        while (scanner.hasNextLine()) {
            final String line = scanner.nextLine();
            words.addAll(Arrays.stream(line.split("\\s+"))
                    .filter(w -> w.length() > 1)
                    .map(w -> w.replace("/", "--"))
                    .collect(Collectors.toList()));
        }

        return words;
    }
}
