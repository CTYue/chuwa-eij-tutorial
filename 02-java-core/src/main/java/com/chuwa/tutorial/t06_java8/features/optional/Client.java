package com.chuwa.tutorial.t06_java8.features.optional;

import java.util.Optional;

/**
 * @author b1go
 * @date 5/13/22 2:41 PM
 */
public class Client {

    public int getMobileScreenWidth(Mobile7 mobile7) {
        if (mobile7 != null) {
            DisplayFeatures7 df = mobile7.getDisplayFeatures();
            if (df != null) {
                ScreenResolution sr = df.getResolution();
                if (sr != null) {
                    return sr.getWidth();
                }
            }
        }

        return 0;
    }

    public int getMobileScreenWidthOptional(Mobile mobile) {
        return Optional.ofNullable(mobile).flatMap(Mobile::getDisplayFeaturesOptional)
                .flatMap(DisplayFeatures::getResolutionOptional)
                .map(ScreenResolution::getWidth)
                .orElse(0);
    }
}
