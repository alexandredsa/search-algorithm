package br.com.searchalgorithm.index.workers;


import br.com.searchalgorithm.index.io.utils.IndexFileReader;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.Callable;

public class IndexTask implements Callable<Map<String, List<String>>> {
    private List<Path> filenames;
    private IndexFileReader fileReader;

    static IndexTask with(List<Path> filenames, IndexFileReader fileReader) {
        IndexTask searchTask = new IndexTask();
        searchTask.filenames = filenames;
        searchTask.fileReader = fileReader;
        return searchTask;
    }

    @Override
    public Map<String, List<String>> call() {
        Map<String, List<String>> result = new HashMap<>();
        this.filenames.forEach(f -> addOccurrencesToResultMap(f,result));

        return result;
    }

    private void addOccurrencesToResultMap(Path f, Map<String, List<String>> result) {
        try {
            TreeSet<String> words = this.fileReader.wordsFromFile(f);
            words.forEach(w -> {
                List<String> occurrences = result.getOrDefault(w, new ArrayList<>());
                occurrences.add(f.getFileName().toString());
                result.put(w, occurrences);
            });
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
