// License: GPL. For details, see Readme.txt file.
package org.openstreetmap.gui.jmapviewer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

import static org.openstreetmap.gui.jmapviewer.FeatureAdapter.tr;

import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.util.HashMap;

import org.openstreetmap.gui.jmapviewer.interfaces.Attributed;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

public class AttributionSupport {

    private Attributed source;

    private Image attrImage;
    private String attrTermsText;
    private String attrTermsUrl;
    public static final Font ATTR_FONT = (OsmMercator.isRetina() ? 
        new Font("Arial", Font.PLAIN, 20) : new Font("Arial", Font.PLAIN, 10));
    public static final Font ATTR_LINK_FONT;

    protected Rectangle attrTextBounds;
    protected Rectangle attrToUBounds;
    protected Rectangle attrImageBounds;

    static {
        HashMap<TextAttribute, Integer> aUnderline = new HashMap<>();
        aUnderline.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        ATTR_LINK_FONT = ATTR_FONT.deriveFont(aUnderline);
    }

    public void initialize(Attributed source) {
        this.source = source;
        boolean requireAttr = source.requiresAttribution();
        if (requireAttr) {
            attrImage = source.getAttributionImage();
            attrTermsText = source.getTermsOfUseText();
            attrTermsUrl = source.getTermsOfUseURL();
            if (attrTermsUrl != null && attrTermsText == null) {
                attrTermsText = tr("Background Terms of Use");
            }
        } else {
            attrImage = null;
            attrTermsUrl = null;
        }
    }

    public void paintAttribution(Graphics g, int width, int height, ICoordinate topLeft, ICoordinate bottomRight,
            int zoom, ImageObserver observer) {
        if (source == null || !source.requiresAttribution()) {
            attrToUBounds = null;
            attrImageBounds = null;
            attrTextBounds = null;
            return;
        }
        Graphics2D g2 = (Graphics2D) g;

        // Draw attribution
        Font font = g2.getFont();
        g2.setFont(ATTR_LINK_FONT);

        // Draw terms of use text
        int termsTextHeight = 0;
        int termsTextY = height;

        if (attrTermsText != null) {
            Rectangle2D termsStringBounds = g2.getFontMetrics().getStringBounds(attrTermsText, g2);
            int textRealHeight = (int) termsStringBounds.getHeight();
            termsTextHeight = textRealHeight - 5;
            int termsTextWidth = (int) termsStringBounds.getWidth();
            termsTextY = height - termsTextHeight;
            int x = 2;
            int y = height - termsTextHeight;
            attrToUBounds = new Rectangle(x, y-termsTextHeight, termsTextWidth, textRealHeight);
            g2.setColor(Color.black);
            g2.drawString(attrTermsText, x + 1, y + 1);
            g2.setColor(Color.white);
            g2.drawString(attrTermsText, x, y);
        } else {
            attrToUBounds = null;
        }

        // Draw attribution logo
        if (attrImage != null) {
            int x = 2;
            int imgWidth = attrImage.getWidth(observer);
            int imgHeight = attrImage.getHeight(observer);
            int y = termsTextY - imgHeight - termsTextHeight - 5;
            attrImageBounds = new Rectangle(x, y, imgWidth, imgHeight);
            g2.drawImage(attrImage, x, y, null);
        } else {
            attrImageBounds = null;
        }

        g2.setFont(ATTR_FONT);
        String attributionText = source.getAttributionText(zoom, topLeft, bottomRight);
        if (attributionText != null) {
            Rectangle2D stringBounds = g2.getFontMetrics().getStringBounds(attributionText, g2);
            int textHeight = (int) stringBounds.getHeight() - 5;
            int x = width - (int) stringBounds.getWidth();
            int y = height - textHeight;
            g2.setColor(Color.black);
            g2.drawString(attributionText, x + 1, y + 1);
            g2.setColor(Color.white);
            g2.drawString(attributionText, x, y);
            attrTextBounds = new Rectangle(x, y-textHeight, (int) stringBounds.getWidth(), (int) stringBounds.getHeight());
        } else {
            attrTextBounds = null;
        }

        g2.setFont(font);
    }

    public boolean handleAttributionCursor(Point p) {
        if (attrTextBounds != null && attrTextBounds.contains(p)) {
            return true;
        } else if (attrImageBounds != null && attrImageBounds.contains(p)) {
            return true;
        } else if (attrToUBounds != null && attrToUBounds.contains(p)) {
            return true;
        }
        return false;
    }

    public boolean handleAttribution(Point p, boolean click) {
        if (source == null || !source.requiresAttribution())
            return false;

        if (attrTextBounds != null && attrTextBounds.contains(p)) {
            String attributionURL = source.getAttributionLinkURL();
            if (attributionURL != null) {
                if (click) {
                    FeatureAdapter.openLink(attributionURL);
                }
                return true;
            }
        } else if (attrImageBounds != null && attrImageBounds.contains(p)) {
            String attributionImageURL = source.getAttributionImageURL();
            if (attributionImageURL != null) {
                if (click) {
                    FeatureAdapter.openLink(source.getAttributionImageURL());
                }
                return true;
            }
        } else if (attrToUBounds != null && attrToUBounds.contains(p)) {
            String termsOfUseURL = source.getTermsOfUseURL();
            if (termsOfUseURL != null) {
                if (click) {
                    FeatureAdapter.openLink(termsOfUseURL);
                }
                return true;
            }
        }
        return false;
    }

}

