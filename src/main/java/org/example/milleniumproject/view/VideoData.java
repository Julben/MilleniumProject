package org.example.milleniumproject.view;

import java.util.prefs.Preferences;

public class VideoData {
    private static final Preferences preferences = Preferences.userNodeForPackage(VideoData.class);
    private static final String BRIGHTNESS_KEY = "brightness";

    public static double getBrightness() {
        return preferences.getDouble(BRIGHTNESS_KEY, 0.0);
    }

    public static void setBrightness(double brightness) {
        preferences.putDouble(BRIGHTNESS_KEY, brightness);
    }
}
