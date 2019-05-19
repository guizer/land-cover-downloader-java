package com.github.guizer.landcover_downloader_java;

/** Represents an url and its arguments.
 * @author Doug Lowe
 * @author www.LoweWriter.com
 * @version 1.5
 * @since 1.0
 */
public class Url {

    /* latitude of the center of the map */
    private final float latitude; // required

    /* longitude of the center of the map, */
    private final float longitude; // required

    /* zoom level of the map, which determines the magnification level of the map */
    private final int zoom; //required

    /* width of the map image */
    private final int width; // required

    /* heigth of the map image */
    private final int height; // required

    /* API key */
    private final String key; //required

    /* number of pixels that are returned */
    private final int scale;

    /* format of the resulting image */
    private final String format;

    /* type of map to construct */
    private final String mapType;

    /* digital signature used to verify that any site generating requests using your API key is authorized to do so */
    private final String signature;

    /**
     *
     * @param builder
     */
    public Url(UrlBuilder builder) {
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.zoom = builder.zoom;
        this.width = builder.width;
        this.height = builder.height;
        this.key = builder.key;
        this.scale = builder.scale;
        this.format = builder.format;
        this.mapType = builder.mapType;
        this.signature = builder.signature;

    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public int getZoom() {
        return zoom;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getScale() {
        return scale;
    }

    public String getFormat() {
        return format;
    }

    public String getMapType() {
        return mapType;
    }

    public String getKey() {
        return key;
    }

    public String getSignature() {
        return signature;
    }

    @Override
    public String toString() {

        String url = "https://maps.googleapis.com/maps/api/staticmap?";

        url += "&center=" + latitude + "," + longitude;
        url += "&zoom=" + zoom;
        url += "&size=" + width + "x" + (height + 25*scale);
        url += "&key=" + key;
        url += "&scale=" + scale;
        url += "&format=" + format;
        url += "&maptype=" + mapType;

        if(!signature.isEmpty()) {
            url += "&signature=" + signature;
        }

        return url;
    }

    /**
     * Class used to build Url objects
     */
    public static class UrlBuilder {

        private Url url;
        private final float latitude;
        private final float longitude;
        private final int zoom;
        private final int width;
        private final int height;
        private final String key;
        private int scale;
        private String format;
        private String mapType;
        private String signature;

        /**
         *
         * @param latitude
         * @param longitude
         * @param zoom
         * @param width
         * @param height
         * @param key
         */
        public UrlBuilder(float latitude, float longitude, int zoom, int width, int height, String key) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.zoom = zoom;
            this.width = width;
            this.height = height;
            this.key = key;
        }

        /**
         *
         * @param scale
         * @return this url builder with its scale modified
         */
        public UrlBuilder withScale(int scale) {
            this.scale = scale;
            return this;
        }

        /**
         *
         * @param format format of the image
         * @return this url builder with its format modified
         */
        public UrlBuilder withFormat(String format) {
            this.format = format;
            return this;
        }

        /**
         *
         * @param mapType
         * @return this url builder with its map type modified
         */
        public UrlBuilder withMapType(String mapType) {
            this.mapType = mapType;
            return this;
        }

        /**
         *
         * @param signature
         * @return this url builder with its signature modified
         */
        public UrlBuilder withSignature(String signature) {
            this.signature = signature;
            return this;
        }

        /**
         *
         * @return
         */
        public Url build() {
            return new Url(this);
        }

    }
}
