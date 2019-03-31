package br.com.searchalgorithm.file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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

}
