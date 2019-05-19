package com.github.guizer.landcover_downloader_java.validators;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;


public class LongitudeValidator implements IParameterValidator {

    @Override
    public void validate(String name, String value) throws ParameterException {

        // a longitude should be between 0 and 360 degrees
        Float lon = Float.valueOf(value);
        if (lon < -180 || lon > 180) {
            throw new ParameterException(name + " should be a float between 0 and 360.");
        }
    }
}
