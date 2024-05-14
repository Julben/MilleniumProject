package org.example.milleniumproject.model;

import javafx.animation.Timeline;
import javafx.scene.control.Label;

public class chrono {

    static void ResetChrono(Timeline timeline1, Label timerLabel, String chrono, int[] remainingSeconds, Timeline timeline2){
        int reset = Integer.parseInt(chrono);
        timeline1.stop();
        timerLabel.setText(chrono);
        remainingSeconds[0] = reset;
        timeline2.play();
    }
}
