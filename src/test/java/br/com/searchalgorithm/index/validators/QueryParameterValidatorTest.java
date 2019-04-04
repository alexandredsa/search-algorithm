package br.com.searchalgorithm.index.validators;

import org.junit.Test;

import static org.junit.Assert.*;

public class QueryParameterValidatorTest {

    @Test(expected = Exception.class)
    public void validate() throws Exception {
        BasicValidator queryParameterValidator = new QueryParameterValidator();
        queryParameterValidator.validate("   ");
    }
}