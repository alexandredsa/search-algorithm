package br.com.searchalgorithm.index.io.utils;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.TreeSet;

public interface IndexFileReader {
    List<Path> getFilenames(String path);
    TreeSet<String> wordsFromFile(Path path) throws FileNotFoundException;
}
