package br.com.searchalgorithm.index.workers;


import br.com.searchalgorithm.index.io.utils.FileReader;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.Callable;

public class IndexTask implements Callable<Map<String, List<String>>> {
    private List<Path> filenames;

    static IndexTask with(List<Path> filenames) {
        IndexTask searchTask = new IndexTask();
        searchTask.filenames = filenames;
        return searchTask;
    }

    @Override
    public Map<String, List<String>> call() {
        Map<String, List<String>> result = new HashMap<>();
        this.filenames
                    .forEach(f -> {
                        try {
                             TreeSet<String> words = FileReader.wordsFromFile(f);
                             words.forEach(w -> {
                                 List<String> occurrences = result.getOrDefault(w, new ArrayList<>());
                                 occurrences.add(f.getFileName().toString());
                                 result.put(w, occurrences);
                             });
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    });

        return result;
    }
}
