package sample.domain;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AudioMusicPlayer {
    private Clip musicFile;
    //This methodes loads in the audio file, so it is possible to make an object from it.
    public AudioMusicPlayer(String path) {
        try {
            // Makes a file
            File audioFile = new File(path);
            // Makes sure the file exists
            if (audioFile.exists()) {
                // Makes a player
                AudioInputStream sound = AudioSystem.getAudioInputStream(audioFile);
                // Puts the input file into the player
                this.musicFile = AudioSystem.getClip();
                // Ready the file
                musicFile.open(sound);
            } else {
                // Bad path exception
                throw new RuntimeException("Sound not found: " + path);
            }
            // Makes sure there is connection to the file on the pc, so that there is a constant dataflow
        } catch (MalformedURLException e) {
            throw new RuntimeException("Sound: Malformed URL: " + e);
            // Bad file type
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
            // All exceptions regarding data flow to and from program
        } catch (IOException e) {
            throw new RuntimeException("Sound: Input/Output Error: " + e);
            // Makes sure that 1 line cant be played by 2 diffrent callers at one time.
        } catch (LineUnavailableException e) {
            throw new RuntimeException("Sound: Line Unavailable: " + e);
        }

    }
    // Loops the audio file infinate
    public void musicPlayerInfinity() {
        this.musicFile.loop(Clip.LOOP_CONTINUOUSLY);
    }
    // Plays the audio file once
    public void AudioPlayer() {
        // Set the audio file to the start
        this.musicFile.setFramePosition(0);
        // when loop count is 0, the audio is played once
        this.musicFile.loop(0);
        this.musicFile.start();
    }
    // Stops a playing audio file
    public void AudioStop() {
        this.musicFile.stop();
    }


}