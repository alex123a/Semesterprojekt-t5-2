package sample.domain;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AudioMusicPlayer {
    private String path;
    private Clip musicFile;

    public AudioMusicPlayer(String path) {
        try {
            File audioFile = new File(path);
            if (audioFile.exists()) {
                AudioInputStream sound = AudioSystem.getAudioInputStream(audioFile);
                this.musicFile = AudioSystem.getClip();
                musicFile.open(sound);
            } else {
                throw new RuntimeException("Sound not found: " + path);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Sound: Malformed URL: " + e);
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException("Sound: Unsupported Audio File: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Sound: Input/Output Error: " + e);
        } catch (LineUnavailableException e) {
            throw new RuntimeException("Sound: Line Unavailable: " + e);
        }

    }

    public void musicPlayerInfinity() {
        this.musicFile.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void AudioPlayer() {
        this.musicFile.setFramePosition(0);
        this.musicFile.loop(0);
        this.musicFile.start();
    }

    public void AudioStop() {
        this.musicFile.stop();
    }


}