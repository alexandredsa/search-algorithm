package br.com.searchalgorithm.index;

import br.com.searchalgorithm.index.utils.file.FileReader;
import br.com.searchalgorithm.index.validators.FileParameterValidator;
import br.com.searchalgorithm.index.workers.IndexExecutor;

import java.nio.file.Path;
import java.util.List;

public class IndexRunner {
    private static final int BATCH_SIZE = 350;
    private static final FileParameterValidator fileParameterValidator = new FileParameterValidator();


    public static void main(String[] args) throws Exception {
        validateArgs(args);
        final String path = args[0];
        List<Path> filenames = FileReader.getFilenames(path);
        IndexExecutor.with(filenames, BATCH_SIZE, path).run();
        System.exit(0);
    }

    private static void validateArgs(String[] args) throws Exception {
        if(args.length == 0) {
            throw new IllegalArgumentException("Should pass 1 argument: " +
                    "\n1)An argument representing a valid path for a directory");
        }

        fileParameterValidator.validate(args[0]);
    }


}
