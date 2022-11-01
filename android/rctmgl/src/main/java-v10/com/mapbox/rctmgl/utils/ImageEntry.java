package com.mapbox.rctmgl.utils;

import com.mapbox.maps.ImageContent;
import com.mapbox.maps.ImageStretches;

import java.util.ArrayList;

public class ImageEntry {
    public String uri;
    public double scale = 1.0;
    public static final double defaultScale = 0.0;
    public ArrayList<ImageStretches> stretchX = new ArrayList<>();
    public ArrayList<ImageStretches> stretchY = new ArrayList<>();
    public ImageContent content;

    public ImageEntry(String _uri, Double _scale) {
        uri = _uri;
        scale = _scale;
    }

    public ImageEntry(String _uri) {
        uri = _uri;
        scale = ImageEntry.defaultScale;
    }

    public double getScaleOr(double v) {
        if (scale == ImageEntry.defaultScale) {
            return v;
        } else {
            return scale;
        }
    }

    public void setStretchOptions(ImageStretchableOptions options) {
        ArrayList<ImageStretches> sx = new ArrayList<>();
        ArrayList<ImageStretches> sy = new ArrayList<>();
        ImageContent c = null;
        float multiplier = (float) scale;
        for (int i = 0; i < options.stretchX.size(); i++) {
            ImageStretches stretches = options.stretchX.get(i);
            sx.add(
                    new ImageStretches(
                            stretches.getFirst() * multiplier,
                            stretches.getSecond() * multiplier
                    )
            );
        }
        for (int i = 0; i < options.stretchY.size(); i++) {
            ImageStretches stretches = options.stretchY.get(i);
            sy.add(
                    new ImageStretches(
                            stretches.getFirst() * multiplier,
                            stretches.getSecond() * multiplier
                    )
            );
        }
        if (options.content != null) {
            c = new ImageContent(
                    options.content.getLeft() * multiplier,
                    options.content.getTop() * multiplier,
                    options.content.getRight() * multiplier,
                    options.content.getBottom() * multiplier
            );
        }
        stretchX = sx;
        stretchY = sy;
        content = c;
    }

    public ImageStretchableOptions getStretchOptions() {
        return new ImageStretchableOptions(stretchX, stretchY, content);
    }
}
