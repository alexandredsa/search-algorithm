package br.com.searchalgorithm.index.engine;

import br.com.searchalgorithm.index.repositories.IndexRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

public final class SearchExecutor {
    private String[] terms;
    private final ExecutorService threadpool;
    private IndexRepository indexRepository;

    private SearchExecutor() {
        this.threadpool = Executors.newFixedThreadPool(3);
    }

    public static SearchExecutor with(IndexRepository indexRepository, String[] terms) {
        SearchExecutor searchExecutor = new SearchExecutor();
        searchExecutor.indexRepository = indexRepository;
        searchExecutor.terms = terms;
        return searchExecutor;
    }

    public TreeSet<String> run() {
        List<Future<TreeSet<String>>> futures = new ArrayList<>();

        for (String term : terms) {
            SearchTask task = SearchTask.with(this.indexRepository, term);
            futures.add(this.threadpool.submit(task));
        }

        while (futures.stream().anyMatch(f -> !f.isDone())) {}


        List<TreeSet<String>> result = futures.stream().map(r -> {
            try {
                return r.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        return result.stream().reduce(result.get(0), (a, b) -> {
            a.retainAll(b);
            return a;
        });
    }
}
