package br.com.searchalgorithm.index.repositories;

import br.com.searchalgorithm.index.models.IndexModel;

import java.io.IOException;

public interface IndexRepository {
    void save(IndexModel model) throws IOException;
    IndexModel retrieve(String term) throws IOException, ClassNotFoundException;
}
