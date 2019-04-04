package br.com.searchalgorithm.index.repositories;

import br.com.searchalgorithm.index.io.FileManager;
import br.com.searchalgorithm.index.models.IndexModel;

import java.io.File;
import java.io.IOException;

public class IndexRepositoryImpl implements IndexRepository {
    public static final String CUSTOM_EXTENSION_TYPE = "llabs";

    private String context;
    private FileManager indexFileManager;

    public IndexRepositoryImpl(String context, FileManager indexFileManager) {
        this.context = context;
        this.indexFileManager = indexFileManager;
    }

    public void save(IndexModel model) throws IOException {
        final String path = this.generatePath(model.getTerm());
        this.indexFileManager.write(path, model);
    }

    public IndexModel retrieve(String term) throws IOException, ClassNotFoundException {
        term = term.replace("/", "--");
        final String path = this.generatePath(term);
        return (IndexModel) this.indexFileManager.read(path);
    }

    private String generatePath(String term) {
        String basePath = String.format("%s/data", this.context);
        File base = new File(basePath);

        if(!base.exists()) {
            base.mkdirs();
        }

        return String.format("%s/%s.%s", basePath, term, CUSTOM_EXTENSION_TYPE);
    }
}
