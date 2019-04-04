package br.com.searchalgorithm.index.validators;

public class QueryParameterValidator implements BasicValidator {
    @Override
    public void validate(String arg) throws Exception {
        if(arg.trim().length() == 0) {
            throw new RuntimeException("Query parameter should not be empty");
        }
    }
}
