package br.com.searchalgorithm.index;


import br.com.searchalgorithm.index.engine.SearchExecutor;
import br.com.searchalgorithm.index.io.FileManager;
import br.com.searchalgorithm.index.io.IndexFileManager;
import br.com.searchalgorithm.index.repositories.IndexRepository;
import br.com.searchalgorithm.index.repositories.IndexRepositoryImpl;
import br.com.searchalgorithm.index.validators.BasicValidator;
import br.com.searchalgorithm.index.validators.FileParameterValidator;
import br.com.searchalgorithm.index.validators.QueryParameterValidator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.TreeSet;

public class Search {
    private static final BasicValidator fileParameterValidator = new FileParameterValidator();
    private static final BasicValidator queryParameterValidator = new QueryParameterValidator();


    public static void main(String[] args) throws Exception {
        validateArgs(args);

        final String path = args[0];
        final String terms = args[1];
        final FileManager fileManager = new IndexFileManager();

        final IndexRepository indexRepository = new IndexRepositoryImpl(path, fileManager);
        TreeSet<String> result = SearchExecutor.with(indexRepository, terms.split(" ")).run();
        displayResult(result, terms);
        System.exit(0);
    }

    private static void displayResult(TreeSet<String> result, String terms) {
        System.out.println(String.format("Foram encontradas %d ocorrências pelo termo %s",
                result.size(), String.join(" ", terms)));

        System.out.println(String.format("Os arquivos que possuem \"%s\" são:", terms));
        result.forEach(System.out::println);
    }


    private static void validateArgs(String[] args) throws Exception {
        if(args.length == 0) {
            throw new IllegalArgumentException("Should pass 2 arguments: " +
                    "\n1)An argument representing a valid path for a directory" +
                    "\n2)An argument representing a query like: \"walt disney\"");
        }

        fileParameterValidator.validate(args[0]);
        queryParameterValidator.validate(args[1]);
    }
}
