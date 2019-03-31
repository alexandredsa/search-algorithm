package br.com.searchalgorithm.engine;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;

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
        return null;
    }
}
