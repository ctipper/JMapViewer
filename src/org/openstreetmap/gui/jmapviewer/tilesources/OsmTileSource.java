// License: GPL. For details, see Readme.txt file.
package org.openstreetmap.gui.jmapviewer.tilesources;

import java.io.IOException;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

/**
 * OSM Tile source.
 */
public class OsmTileSource {

    /**
     * The default "Mapnik" OSM tile source.
     */
    public static class Mapnik extends AbstractOsmTileSource {

        private static final String PATTERN = "https://%s.tile.openstreetmap.org";

        private static final String[] SERVER = {"a", "b", "c"};

        private int serverNum;

        /**
         * Constructs a new {@code "Mapnik"} tile source.
         */
        public Mapnik() {
            super("Mapnik", PATTERN, "MAPNIK");
        }

        @Override
        public String getBaseUrl() {
            String url = String.format(this.baseUrl, new Object[] {SERVER[serverNum]});
            serverNum = (serverNum + 1) % SERVER.length;
            return url;
        }
    }

    /**
     * The "Cycle Map" OSM tile source.
     */
    public static class CycleMap extends AbstractOsmTileSource {

        private static final String API_KEY = "API_KEY_HERE";

        private static final String PATTERN = "https://%s.tile.thunderforest.com/cycle";

        private static final String[] SERVER = { "a", "b", "c" };

        private int serverNum;

        /**
         * Constructs a new {@code CycleMap} tile source.
         */
        public CycleMap() {
            super("OSM Cycle Map", PATTERN, "opencyclemap");
        }

        @Override
        public String getBaseUrl() {
            String url = String.format(this.baseUrl, new Object[] {SERVER[serverNum]});
            serverNum = (serverNum + 1) % SERVER.length;
            return url;
        }

        @Override
        public int getMaxZoom() {
            return 18;
        }

        @Override
        public String getTileUrl(int zoom, int tilex, int tiley) throws IOException {
            return this.getBaseUrl() + getTilePath(zoom, tilex, tiley); // + "?apikey=" + API_KEY;
        }

        @Override
        public String getAttributionText(int zoom, ICoordinate topLeft, ICoordinate botRight) {
            return "Maps © Thunderforest, Data © OpenStreetMap contributors";
        }

        @Override
        public String getAttributionLinkURL() {
            return "http://www.thunderforest.com/";
        }
    }

    /**
     * The "Transport Map" OSM tile source.
     */
    public static class TransportMap extends AbstractOsmTileSource {

        private static final String API_KEY = "API_KEY_HERE";

        private static final String PATTERN = "https://%s.tile.thunderforest.com/transport";

        private static final String[] SERVER = { "a", "b", "c" };

        private int serverNum;

        /**
         * Constructs a new {@code TransportMap} tile source.
         */
        public TransportMap() {
            super("OSM Transport Map", PATTERN, "osmtransportmap");
        }

        @Override
        public String getBaseUrl() {
            String url = String.format(this.baseUrl, new Object[] {SERVER[serverNum]});
            serverNum = (serverNum + 1) % SERVER.length;
            return url;
        }

        @Override
        public int getMaxZoom() {
            return 18;
        }

        @Override
        public String getTileUrl(int zoom, int tilex, int tiley) throws IOException {
            return this.getBaseUrl() + getTilePath(zoom, tilex, tiley); // + "?apikey=" + API_KEY;
        }

        @Override
        public String getAttributionText(int zoom, ICoordinate topLeft, ICoordinate botRight) {
            return "Maps © Thunderforest, Data © OpenStreetMap contributors";
        }

        @Override
        public String getAttributionLinkURL() {
            return "http://www.thunderforest.com/";
        }
    }

    /**
     * The "Landscape Map" OSM tile source.
     */
    public static class LandscapeMap extends AbstractOsmTileSource {

        private static final String API_KEY = "API_KEY_HERE";

        private static final String PATTERN = "https://%s.tile.thunderforest.com/landscape";

        private static final String[] SERVER = { "a", "b", "c" };

        private int serverNum;

        /**
         * Constructs a new {@code LandscapeMap} tile source.
         */
        public LandscapeMap() {
            super("OSM Landscape Map", PATTERN, "osmlandscapemap");
        }

        @Override
        public String getBaseUrl() {
            String url = String.format(this.baseUrl, new Object[] {SERVER[serverNum]});
            serverNum = (serverNum + 1) % SERVER.length;
            return url;
        }

        @Override
        public int getMaxZoom() {
            return 18;
        }

        @Override
        public String getTileUrl(int zoom, int tilex, int tiley) throws IOException {
            return this.getBaseUrl() + getTilePath(zoom, tilex, tiley); // + "?apikey=" + API_KEY;
        }

        @Override
        public String getAttributionText(int zoom, ICoordinate topLeft, ICoordinate botRight) {
            return "Maps © Thunderforest, Data © OpenStreetMap contributors";
        }

        @Override
        public String getAttributionLinkURL() {
            return "http://www.thunderforest.com/";
        }
    }

    /**
     * The "Outdoors Map" OSM tile source.
     */
    public static class OutdoorsMap extends AbstractOsmTileSource {

        private static final String API_KEY = "API_KEY_HERE";

        private static final String PATTERN = "https://%s.tile.thunderforest.com/outdoors";

        private static final String[] SERVER = { "a", "b", "c" };

        private int serverNum;

        /**
         * Constructs a new {@code OutdoorsMap} tile source.
         */
        public OutdoorsMap() {
            super("OSM Outdoors Map", PATTERN, "osmoutdoorsmap");
        }

        @Override
        public String getBaseUrl() {
            String url = String.format(this.baseUrl, new Object[] {SERVER[serverNum]});
            serverNum = (serverNum + 1) % SERVER.length;
            return url;
        }

        @Override
        public int getMaxZoom() {
            return 18;
        }

        @Override
        public String getTileUrl(int zoom, int tilex, int tiley) throws IOException {
            return this.getBaseUrl() + getTilePath(zoom, tilex, tiley); // + "?apikey=" + API_KEY;
        }

        @Override
        public String getAttributionText(int zoom, ICoordinate topLeft, ICoordinate botRight) {
            return "Maps © Thunderforest, Data © OpenStreetMap contributors";
        }

        @Override
        public String getAttributionLinkURL() {
            return "http://www.thunderforest.com/";
        }
    }

}
