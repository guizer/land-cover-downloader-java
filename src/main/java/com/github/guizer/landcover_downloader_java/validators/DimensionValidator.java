package com.github.guizer.landcover_downloader_java.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;


public class DimensionValidator implements IParameterValidator {

    public static final int MAX = 10000;

    public static final int MIN = 100;


    @Override
    public void validate(String name, String value) throws ParameterException {

        Integer dim = Integer.valueOf(value);

        if (dim < MIN || dim > MAX) {
            throw new ParameterException(name + " should be an integer between " + MIN + " and " + MAX + ".");
        }
    }
}
