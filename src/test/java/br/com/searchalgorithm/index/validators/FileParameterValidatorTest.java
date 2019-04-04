package br.com.searchalgorithm.index.validators;

import org.junit.Test;
import sun.jvm.hotspot.types.basic.BasicVtblAccess;

import static org.junit.Assert.*;

public class FileParameterValidatorTest {

    @Test(expected = Exception.class)
    public void validate() throws Exception {
        BasicValidator fileParameterValidator = new FileParameterValidator();
        fileParameterValidator.validate("  ");
    }
}