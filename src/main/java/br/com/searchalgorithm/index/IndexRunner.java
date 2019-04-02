package br.com.searchalgorithm.index;

import br.com.searchalgorithm.index.utils.file.FileReader;
import br.com.searchalgorithm.index.workers.IndexExecutor;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class IndexRunner {
    private static final String PARAM_CONTEXT_PATH = "path";
    private static final int BATCH_SIZE = 350;

    public static void main(String[] args) {
        final String path = System.getProperty(PARAM_CONTEXT_PATH, Paths.get(".").toAbsolutePath().normalize().toString());
        List<Path> filenames = FileReader.getFilenames("/home/oem/Documentos/movies");
        IndexExecutor.with(filenames, BATCH_SIZE, "/home/oem/Documentos/movies").run();
        System.exit(0);
    }
}
