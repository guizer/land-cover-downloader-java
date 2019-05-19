package com.github.guizer.landcover_downloader_java.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.util.Arrays;


public class MapTypeValidator implements IParameterValidator
{

    public static final String[] VALID_MAP_TYPES = {"roadmap","satellite","terrain","hybrid"};

    @Override
    public void validate(String name, String value) throws ParameterException {
        if (!Arrays.asList(VALID_MAP_TYPES).contains(value)) {
            throw new ParameterException("The specified map type " + value + "is not valid. " +
                    "Valid map types are 'roadmap', 'satellite', 'terrain' and 'hybrid'");
        }
    }
}