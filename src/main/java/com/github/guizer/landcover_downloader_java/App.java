package com.github.guizer.landcover_downloader_java;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * Land Cover Downloader
 *
 */
public class App {
    private JCommander commander;

    public static final int TILE_SIZE = 500;

    public static final float RADIUS = 6371008;

    /**
     *
     * @param latitude
     * @param zoom
     * @return
     */
    public float metersPerPixel(float latitude, int zoom) {
        return (float) (156543.033928 * Math.cos(latitude * Math.PI/180.) / Math.pow(2, zoom));
    }

    /**
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static BufferedImage download(String url) throws IOException {
        try {
            BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
            return ImageIO.read(in);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     *
     * @param image
     * @param path
     * @throws IOException
     */
    public static void write(BufferedImage image, String path) throws IOException {
        try {
            File file = new File(path);
            ImageIO.write(image, "png", file);
        } catch(IOException e) {
            throw e;
        }
    }

    /**
     *
     * @param args
     */
    public void run(String [] args) {

        Arguments arguments = new Arguments();

        // parse and validate the command line arguments
        commander = JCommander.newBuilder()
                .addObject(arguments)
                .build();

        try {
            commander.parse(args);
        } catch (ParameterException e) {

            // display the error
            e.printStackTrace();

            // display the help
            commander.usage();

            // quit
            return;
        }

        // check if the help is requested
        if (arguments.isHelp()) {

            commander.usage();

            return;
        }

        String key = arguments.getKey();

        int zoom = arguments.getZoom();

        int scale = arguments.getScale();

        // number of pixels per meters for the latitude of the origin
        float mpp = metersPerPixel(arguments.getLatitude(), zoom);

        // number of tiles along the horizontal axis
        int nx = (int)(((float)arguments.getWidth() / mpp) / (float)TILE_SIZE) + 1;

        // number of tiles along the vertical axis
        int ny = (int)(((float)arguments.getHeight() / mpp) / (float)TILE_SIZE) + 1;

        // number of tiles to download
        int n = nx*ny;

        // store tha cooridnates of the center of the tiles in arrays
        float[][] lats = new float[ny][nx];
        float[][] lons = new float[ny][nx];

        lats[0][0] = arguments.getLatitude();
        lons[0][0] = arguments.getLongitude();

        // create an empty image
        BufferedImage image = new BufferedImage(nx*TILE_SIZE*scale, ny*TILE_SIZE*scale, BufferedImage.TYPE_INT_RGB);

        // number of downloaded tiles
        int count = 0;

        // Download the tiles and edit the image
        for(int i = 0; i<ny; i++) {
            for(int j = 0; j<nx; j++) {

                float lon = lons[0][0];
                float lat = lats[0][0];

                if(j>0) {
                    lon = lons[i][j-1];
                    lat = lats[i][j-1];
                } else if (j == 0 && i > 0) {
                    lon = lons[i-1][0];
                    lat = lats[i-1][0];
                }

                float dLat = 0;
                float dLon = 0;

                mpp = metersPerPixel(lat, zoom);

                if(j>0) {
                    dLon = mpp * TILE_SIZE/ (RADIUS * (float) Math.cos(lat * Math.PI/180.));
                }

                if(j==0 && i>0) {
                    dLat = mpp * TILE_SIZE / RADIUS;
                }

                lats[i][j] = lat - (float) (180./Math.PI * dLat);
                lons[i][j] = lon + (float) (180./Math.PI * dLon);

                String url = new Url.UrlBuilder(lats[i][j], lons[i][j], zoom, TILE_SIZE, TILE_SIZE, key)
                        .withFormat(arguments.getFormat())
                        .withMapType(arguments.getMapType())
                        .withScale(arguments.getScale())
                        .withSignature(arguments.getSignature())
                        .build()
                        .toString()
                        ;

                count++;

                System.out.println("Downloading tile " + count + "/" + n + ", position=(" + i + "," + j + "), lat=" + lats[i][j] + ", lon=" + lons[i][j]);

                // download the tile
                try {
                    BufferedImage tile = download(url);

                    // replace the pixel of the big image with the ones of the tile
                    for(int k = 0; k<TILE_SIZE*scale; k++) {
                        for(int l = 0; l<TILE_SIZE*scale; l++) {
                            // update the pixeks
                            image.setRGB(l+j*TILE_SIZE*scale, k+i*TILE_SIZE*scale, tile.getRGB(l,k));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
        }

        // Save the image
        try {
            System.out.println("Saving the merged image to " + arguments.getOutput());
            write(image, arguments.getOutput());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public static void main( String[] args )
    {
        App app = new App();

        // run the app
        app.run(args);
    }
}
