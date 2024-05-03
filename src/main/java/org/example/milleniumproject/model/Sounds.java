/*package org.example.millenium_vfirst.model;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Sounds implements LineListener {
    // Instance variables
    private Map<String, ArrayList<File>> musicLists; // Map des listes de musique avec des identifiants uniques
    private Map<String, Integer> currentTrackIndices; // Map des indices de morceaux actuellement en lecture
    private Map<String, Clip> clips; // Map des objets Clip pour chaque liste de musique
    private Map<String, Boolean> isPlaying; // Map indiquant si la musique est en cours de lecture pour chaque liste
    private static Sounds instance;

    /**
     * Constructs a new instance of Sounds with an empty map of music lists.
     */
    /*private Sounds() {
        musicLists = new HashMap<>();
        currentTrackIndices = new HashMap<>();
        clips = new HashMap<>();
        isPlaying = new HashMap<>();
    }

    /**
     * This method returns a single instance of the Sounds class with the specified identifier.
     * @param identifier unique identifier for the instance
     * @param musicList ArrayList of music files to play.
     * @return the instance of the Sounds class
     */
    /*public static Sounds getInstance(String identifier, ArrayList<File> musicList) {
        if (instance == null) {
            instance = new Sounds(); // If the instance doesn't exist, create it
        }
        instance.addMusicList(identifier, musicList); // Add the music list to the instance
        return instance;
    }

    /**
     * Add a music list with the specified identifier to the map.
     * @param identifier unique identifier for the music list
     * @param musicList ArrayList of music files to play.
     */
    /*public void addMusicList(String identifier, ArrayList<File> musicList) {
        musicLists.put(identifier, musicList);
        currentTrackIndices.put(identifier, 0);
        isPlaying.put(identifier, false);
        try {
            Clip clip = AudioSystem.getClip();
            clip.addLineListener(this);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(musicList.get(0));
            clip.open(inputStream);
            clips.put(identifier, clip);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Get the FloatControl object used to adjust the volume
     * @param identifier unique identifier for the music list
     * @return the floatcontrol object of the volume
     */
    /*public FloatControl getVolumeControl(String identifier) {
        return (FloatControl) clips.get(identifier).getControl(FloatControl.Type.MASTER_GAIN);
    }

    /**
     * Start playing the audio for the specified music list.
     * @param identifier unique identifier for the music list
     */
    /*public void play(String identifier) {
        Clip clip = clips.get(identifier);
        clip.start();
        isPlaying.put(identifier, true);
        FloatControl gainControl = getVolumeControl(identifier);
        gainControl.setValue(-10.0f);
    }

    /**
     * Stop playing the audio for the specified music list.
     * @param identifier unique identifier for the music list
     */
    /*public void stop(String identifier) {
        Clip clip = clips.get(identifier);
        clip.stop();
        isPlaying.put(identifier, false);
    }

    /**
     * Set the volume of the audio being played for the specified music list.
     * @param identifier unique identifier for the music list
     * @param volume new volume of the sound
     */
    /*public void setVolume(String identifier, double volume) {
        Control control = clips.get(identifier).getControl(FloatControl.Type.MASTER_GAIN);
        if (control instanceof FloatControl) {
            FloatControl gainControl = (FloatControl) control;
            float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }

    /**
     * This method is called when a LineEvent occurs on the Clip object
     */
    /**
     * This method is called when a LineEvent occurs on the Clip object
     */
    /*public void update(LineEvent event) {
        String identifier = null;
        for (Map.Entry<String, Clip> entry : clips.entrySet()) {
            if (entry.getValue().equals(event.getLine())) {
                identifier = entry.getKey();
                break;
            }
        }
        if (identifier == null) {
            return; // If the identifier is not found, return
        }
        if (event.getType() == LineEvent.Type.STOP && isPlaying.get(identifier)) { // Check if the event is of type STOP and audio is currently playing
            try {
                Clip clip = clips.get(identifier);
                if (clip.isOpen()) { // Check if the Clip object is open
                    clip.stop(); // Stop playing the current track
                    clip.close(); // Close the current track
                }
                int currentTrackIndex = currentTrackIndices.get(identifier); // Get the current track index for the specified identifier
                currentTrackIndex++; // Increment the current track index
                if (currentTrackIndex >= musicLists.get(identifier).size()) { // Check if the index is out of bounds
                    currentTrackIndex = 0; // Set the index to 0
                }
                // Open the audio stream of the next track in the musicList
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(musicLists.get(identifier).get(currentTrackIndex));
                clip.open(inputStream);
                clip.start(); // Start playing the new track
                currentTrackIndices.put(identifier, currentTrackIndex); // Update the current track index in the map
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
    }


    /**
     * Return true if the sound for the specified music list is currently playing, false otherwise.
     * @param identifier unique identifier for the music list
     * @return true if the sound is playing, false otherwise
     */
    /*public boolean isPlaying(String identifier) {
        return isPlaying.get(identifier);
    }
}*/

