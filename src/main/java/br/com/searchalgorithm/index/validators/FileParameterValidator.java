package br.com.searchalgorithm.index.validators;

import java.io.File;
import java.io.FileNotFoundException;

public class FileParameterValidator implements BasicValidator{

    @Override
    public void validate(String arg) throws Exception {
        File f = new File(arg);

        if(!f.exists()) {
            throw new FileNotFoundException();
        }

        if(!f.isDirectory()) {
            throw new IllegalArgumentException("The path should be a directory");
        }
    }
}
