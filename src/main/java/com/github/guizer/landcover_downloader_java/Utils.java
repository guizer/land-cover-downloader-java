package com.github.guizer.landcover_downloader_java;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 *
 */
public class Utils {

    /**
     * Download an image from its url.
     * @param url the url of the image to download
     * @return the downloaded image
     * @throws IOException
     */
    public static BufferedImage downloadImage(String url) throws IOException {
        try {
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            return ImageIO.read(in);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Writes an image to a file.
     *
     * @param image the image to write
     * @param path the path where the image should be written
     * @param format the format of the image
     * @throws IOException
     */
    public static void writeImage(BufferedImage image, String path, String format) throws IOException {
        try {
            File file = new File(path);
            ImageIO.write(image, format, file);
        } catch(IOException e) {
            throw e;
        }
    }


    /**
     * Return the number of meters per pixel for a given latitude and a zoom level.
     *
     * @param latitude
     * @param zoom
     * @return
     */
    public static float metersPerPixel(float latitude, int zoom) {
        return (float) (156543.033928 * Math.cos(latitude * Math.PI/180.) / Math.pow(2, zoom));
    }


}
