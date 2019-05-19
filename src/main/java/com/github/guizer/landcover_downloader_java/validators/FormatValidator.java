package com.github.guizer.landcover_downloader_java.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.util.Arrays;

/**
 * Checks that the image format parsed from the command line arguments is supported.
 * Supported format are png and jpg.
 *
 */
public class FormatValidator implements IParameterValidator
{
    public static final String[] VALID_FORMATS = {"png","jpg"};

    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!Arrays.asList(VALID_FORMATS).contains(value)) {
            throw new ParameterException("The specified format " + value + "is not valid. Valid formats are 'png' and 'jpg'");
        }
    }
}