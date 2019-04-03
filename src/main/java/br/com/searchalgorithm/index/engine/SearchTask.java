package br.com.searchalgorithm.index.engine;

import br.com.searchalgorithm.index.models.IndexModel;
import br.com.searchalgorithm.index.repositories.IndexRepository;

import java.io.IOException;
import java.util.TreeSet;
import java.util.concurrent.Callable;

public class SearchTask implements Callable<TreeSet<String>> {

    private IndexRepository indexRepository;
    private String term;

    static SearchTask with(IndexRepository indexRepository, String term) {
        SearchTask searchTask = new SearchTask();
        searchTask.indexRepository = indexRepository;
        searchTask.term = term;
        return searchTask;
    }

    @Override
    public TreeSet<String> call() {
        try {
            IndexModel indexModel = this.indexRepository.retrieve(this.term);
            return indexModel.getFilenames();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
