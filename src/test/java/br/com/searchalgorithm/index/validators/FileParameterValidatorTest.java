package br.com.searchalgorithm.index.validators;

import org.junit.Test;

public class FileParameterValidatorTest {

    @Test(expected = Exception.class)
    public void validate() throws Exception {
        BasicValidator fileParameterValidator = new FileParameterValidator();
        fileParameterValidator.validate("  ");
    }
}