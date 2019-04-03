package br.com.searchalgorithm.index.workers;

import br.com.searchalgorithm.index.models.IndexModel;
import br.com.searchalgorithm.index.repositories.IndexRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class IndexExecutor {
    private final IndexRepository indexRepository;
    private final ExecutorService threadpool;

    private List<Path> filenames;
    private int batchSize;

    private IndexExecutor(IndexRepository indexRepository) {
        this.threadpool = Executors.newFixedThreadPool(5);
        this.indexRepository = indexRepository;
    }

    public static IndexExecutor with(IndexRepository indexRepository, List<Path> filenames, int batchSize) {
        IndexExecutor indexExecutor = new IndexExecutor(indexRepository);
        indexExecutor.filenames = filenames;
        indexExecutor.batchSize = batchSize;
        return indexExecutor;
    }

    public void run() {
        Map<String, TreeSet<String>> occurrences = findOccurrences();
        this.persistAll(occurrences);
    }

    private void persistAll(Map<String, TreeSet<String>> occurrences) {
        occurrences.entrySet().stream()
                .map(t -> new IndexModel(t.getKey(), t.getValue()))
                .forEach(t -> {
                    try {
                        this.indexRepository.save(t);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private Map<String, TreeSet<String>> findOccurrences() {
        List<Future<Map<String, List<String>>>> futures = new ArrayList<>();

        for (int i = 0; i < filenames.size(); i += this.batchSize) {
            IndexTask task = IndexTask.with(filenames.subList(i, Math.min(i + this.batchSize, this.filenames.size())));
            futures.add(this.threadpool.submit(task));
        }

        while (futures.stream().anyMatch(f -> !f.isDone())) {}

        Map<String, TreeSet<String>> result = new HashMap<>();
        futures.stream()
                .map(f -> {
                    try {
                        return f.get();
                    } catch (InterruptedException | ExecutionException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .forEach(m -> {
                    m.forEach((k, v) -> {
                        TreeSet<String> filenames = result.getOrDefault(k, new TreeSet<>());
                        filenames.addAll(v);
                        result.put(k, filenames);
                    });
                });

        return result;
    }
}
