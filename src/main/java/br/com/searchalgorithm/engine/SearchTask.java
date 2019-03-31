package br.com.searchalgorithm.engine;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

public class SearchTask implements Callable<List<String>> {
    private List<Path> filenames;
    private String[] terms;

    static SearchTask with(List<Path> filenames, String[] terms) {
        SearchTask searchTask = new SearchTask();
        searchTask.filenames = filenames;
        searchTask.terms = terms;
        return searchTask;
    }

    @Override
    public List<String> call() {
        return filenames.stream().map(f -> {
            PatternMatcher patternMatcher = new PatternMatcher(f, this.terms);
            try {
                if (patternMatcher.exists()) {
                    return f.getFileName().toString();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            }

            return null;
        })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
