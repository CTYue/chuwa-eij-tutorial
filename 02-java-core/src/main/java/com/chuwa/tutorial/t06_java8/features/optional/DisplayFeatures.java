package com.chuwa.tutorial.t06_java8.features.optional;

import java.util.Optional;

/**
 * @author b1go
 * @date 5/13/22 2:42 PM
 */
public class DisplayFeatures {
    private String size;
    private Optional<ScreenResolution> resolutionOptional;

    public DisplayFeatures(String size, Optional<ScreenResolution> resolutionOptional) {
        this.size = size;
        this.resolutionOptional = resolutionOptional;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Optional<ScreenResolution> getResolutionOptional() {
        return resolutionOptional;
    }

    public void setResolutionOptional(Optional<ScreenResolution> resolutionOptional) {
        this.resolutionOptional = resolutionOptional;
    }
}
