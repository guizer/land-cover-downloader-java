package com.github.guizer.landcover_downloader_java;

/**
 * Land Cover Downloader.
 *
 * This program use Google Map Static API to build large aerial images.
 *
 */
public class Main {

    public static void main( String[] args )
    {
        App app = new App();

        // run the app
        app.run(args);
    }
}
