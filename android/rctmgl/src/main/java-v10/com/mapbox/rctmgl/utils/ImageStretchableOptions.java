package com.mapbox.rctmgl.utils;

import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.mapbox.maps.ImageContent;
import com.mapbox.maps.ImageStretches;

import java.util.ArrayList;
import java.util.Objects;

public class ImageStretchableOptions {
    public ArrayList<ImageStretches> stretchX;
    public ArrayList<ImageStretches> stretchY;
    public ImageContent content;
    public boolean isSDF = false;

    public ImageStretchableOptions(ArrayList<ImageStretches> stretchX, ArrayList<ImageStretches> stretchY, ImageContent content, boolean sdf) {
        this.stretchX = stretchX;
        this.stretchY = stretchY;
        this.content = content;
        this.isSDF = sdf;
    }

    public ImageStretchableOptions(ArrayList<ImageStretches> stretchX, ArrayList<ImageStretches> stretchY) {
        this.stretchX = stretchX;
        this.stretchY = stretchY;
    }

    public ImageStretchableOptions(ReadableMap options) {
        if (options.getType("stretchX") == ReadableType.Array) {
            this.stretchX = this.parseStretchOption(Objects.requireNonNull(options.getArray("stretchX")));
        }
        if (options.getType("stretchY") == ReadableType.Array) {
            this.stretchY = this.parseStretchOption(Objects.requireNonNull(options.getArray("stretchY")));
        }
        if (options.hasKey("content") && options.getType("content") == ReadableType.Array) {
            ArrayList<Object> content = options.getArray("content").toArrayList();
            if (content.size() == 4) {
                this.content = new ImageContent(
                        Float.parseFloat(content.get(0).toString()),
                        Float.parseFloat(content.get(1).toString()),
                        Float.parseFloat(content.get(2).toString()),
                        Float.parseFloat(content.get(3).toString())
                );
            }
        }
        if (options.hasKey("sdf") && options.getType("sdf") == ReadableType.Boolean) {
            this.isSDF = options.getBoolean("sdf");
        }
    }

    private ArrayList<ImageStretches> parseStretchOption(ReadableArray arrayList) {
        ArrayList<ImageStretches> stretches = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            ReadableArray range = arrayList.getArray(i);
            if (range.size() == 2) {
                stretches.add(
                        new ImageStretches(
                                (float) range.getDouble(0),
                                (float) range.getDouble(1)
                        )
                );
            }
        }
        return stretches;
    }
}
