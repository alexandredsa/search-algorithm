package br.com.searchalgorithm.engine;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class SearchExecutor {
    private String[] terms;
    private List<Path> filenames;
    private int batchSize;
    private final ExecutorService threadpool;

    private SearchExecutor() {
        this.threadpool = Executors.newFixedThreadPool(3);
    }

    public static SearchExecutor with(List<Path> filenames, String[] terms, int batchSize) {
        SearchExecutor searchExecutor = new SearchExecutor();
        searchExecutor.filenames = filenames;
        searchExecutor.terms = terms;
        searchExecutor.batchSize = batchSize;
        return searchExecutor;
    }

    public ArrayList<String> run() {
        List<Future<List<String>>> futures = new ArrayList<>();
        TreeSet<String> result = new TreeSet<>();
        for (int i = 0; i < filenames.size(); i += this.batchSize) {
            SearchTask task = SearchTask.with(filenames.subList(i, i + this.batchSize), this.terms);
            futures.add(this.threadpool.submit(task));
        }

        while (futures.stream().anyMatch(f -> !f.isDone())) {}

        futures.forEach(r -> {
            try {
                result.addAll(r.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        });

        return new ArrayList<>(result);
    }
}
