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

        private static final String API_KEY = "4ce9fbeac6a64fdaa9d725181e89082d";
        
        private static final String PATTERN = "http://%s.tile.thunderforest.com/cycle";

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
            return this.getBaseUrl() + getTilePath(zoom, tilex, tiley) + "?apikey=" + API_KEY;
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
