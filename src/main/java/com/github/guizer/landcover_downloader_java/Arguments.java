package com.github.guizer.landcover_downloader_java;

import com.beust.jcommander.Parameter;
import com.github.guizer.landcover_downloader_java.validators.*;


/**
 *
 *
 *
 */
public class Arguments {

    @Parameter(names = { "-latitude", "-lat" }, order = 0, required = true, validateWith = { LatitudeValidator.class },
            description = "Latitude of the center of the image in degrees")
    private Float latitude;

    @Parameter(names = {"-longitude", "-lon" }, order = 1, required = true, validateWith = { LongitudeValidator.class },
            description = "Longitude of the center of the image in degrees")
    private Float longitude;

    @Parameter(names = {"-width", "-w"}, order = 2, required = true, validateWith = { DimensionValidator.class },
            description = "Width of the image in meters")
    private Integer width;

    @Parameter(names = {"-height", "-h"}, order = 3, required = true, validateWith = { DimensionValidator.class },
            description = "Height of the image in meters")
    private Integer height;

    @Parameter(names = {"-zoom", "-z"}, order = 4, required = true, validateWith = { ZoomValidator.class },
            description = "Height of the image in meters")
    private Integer zoom;

    @Parameter(names = {"-key", "-k"}, order = 5, required = true, validateWith = { KeyValidator.class },
            description = "The path to the API key. It will override the value specified in the properties file.")
    private String key;

    @Parameter(names = {"-scale", "-s"}, order = 6, validateWith = { ScaleValidator.class },
            description = "number of pixels that are returned")
    private Integer scale = 1;

    @Parameter(names = {"-format", "-f"}, order = 7, validateWith = { FormatValidator.class },
            description = "format of the resulting image")
    private String format = "png";

    @Parameter(names = {"-maptype", "-m"}, order = 8, validateWith = { MapTypeValidator.class },
            description = "type of map to construct")
    private String mapType = "satellite";

    @Parameter(names = {"-signature"}, order = 9, validateWith = { KeyValidator.class },
            description = "digital signature used to verify that any site generating requests using your API key is authorized to do so")
    private String signature = "";

    @Parameter(names = {"-output", "-o"}, order = 10, validateWith = { PathValidator.class },
            description = "The path of the output image. It will override the location specified in the properties file.")
    private String output;

    @Parameter(names = "--help", order = 6, help = true,
            description = "Display the help.")
    private boolean help;


    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getScale() {
        return scale;
    }

    public void setScale(Integer scale) {
        this.scale = scale;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getMapType() {
        return mapType;
    }

    public void setMapType(String mapType) {
        this.mapType = mapType;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public boolean isHelp() {
        return help;
    }

    public void setHelp(boolean help) {
        this.help = help;
    }
}