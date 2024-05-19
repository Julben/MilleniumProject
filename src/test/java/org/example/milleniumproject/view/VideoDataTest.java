package org.example.milleniumproject.view;

import javafx.beans.property.BooleanProperty;
import org.example.milleniumproject.view.VideoData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VideoDataTest {


    @Test
    void testSetVideoChoose() {
        // Set videoChoose to true
        VideoData.setVideoChoose(true);

        // Verify that videoChoose is true
        assertTrue(VideoData.isVideoChoose());

        // Reset to initial value
        VideoData.setVideoChoose(false);
    }

    @Test
    void testVideoChooseProperty() {
        // Get the BooleanProperty instance
        final BooleanProperty videoChooseProperty = VideoData.videoChooseProperty();

        // Verify that it is not null
        assertTrue(videoChooseProperty != null);
    }

    @Test
    void testIsAnimation() {
        // Test initial value
        assertFalse(VideoData.isAnimation());
    }

    @Test
    void testSetAnimation() {
        // Set animation to true
        VideoData.setAnimation(true);

        // Verify that animation is true
        assertTrue(VideoData.isAnimation());

        // Reset to initial value
        VideoData.setAnimation(false);
    }

    @Test
    void testAnimationProperty() {
        // Get the BooleanProperty instance
        final BooleanProperty animationProperty = VideoData.animationProperty();

        // Verify that it is not null
        assertTrue(animationProperty != null);
    }
}
