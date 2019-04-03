package br.com.searchalgorithm.index.io;

import br.com.searchalgorithm.index.models.IndexModel;

import java.io.IOException;

public interface FileManager {
    void write(String path, IndexModel model) throws IOException;

    Object read(String path) throws IOException, ClassNotFoundException;
}
