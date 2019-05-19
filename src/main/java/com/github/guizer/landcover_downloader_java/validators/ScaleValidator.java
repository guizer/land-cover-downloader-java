package com.github.guizer.landcover_downloader_java.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;


/**
 * Checks that the scale parsed from the command line arguments is valid.
 * Valid values are 1, 2 and 4 if the account associated to the API key is premium.
 *
 */
public class ScaleValidator implements IParameterValidator
{

    @Override
    public void validate(String name, String value) throws ParameterException {
        Integer scale = Integer.valueOf(value);

        if (scale != 1 && scale != 2 && scale != 4) {
            throw new ParameterException("The specified scale is not valid. Valid scales are 1, 2 and 4.");
        }
    }
}