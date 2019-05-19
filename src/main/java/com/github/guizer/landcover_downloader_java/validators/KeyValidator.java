package com.github.guizer.landcover_downloader_java.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;


/**
 * Checks that the API key parsed from the command line arguments is not empty.
 *
 */
public class KeyValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {

        // An api key should be a non empty string
        if(value.isEmpty()) {
            throw new ParameterException("The API key is empty.");
        }
    }
}
