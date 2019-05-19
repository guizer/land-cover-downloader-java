package com.github.guizer.landcover_downloader_java.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;


public class ZoomValidator implements IParameterValidator {

    public static final int MAX = 20;

    public static final int MIN = 1;

    @Override
    public void validate(String name, String value) throws ParameterException {

        Integer dim = Integer.valueOf(value);

        if (dim < MIN || dim > MAX) {
            throw new ParameterException(name + " should be an integer between " + MIN + " and " + MAX + ".");
        }
    }
}
