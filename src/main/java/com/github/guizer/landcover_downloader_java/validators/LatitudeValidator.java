package com.github.guizer.landcover_downloader_java.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;


public class LatitudeValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {

        // a latitude should be between -90 and 90 degree
        Float lat = Float.valueOf(value);
        if (lat < -90 || lat > 90) {
            throw new ParameterException(name + " should be a float between -90 and 90.");
        }
    }
}
