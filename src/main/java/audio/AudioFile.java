package audio;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;



public class AudioFile {
    private String track;
    private Clip clip = null;
    private FloatControl volumeC = null;

    private double wt;
    public AudioFile(String track, double wt) {
        this.track = track;
        this.wt = wt;
    }
    public AudioFormat getFormat()
    {
        return clip.getFormat();

    }

    public void sound() {
        File file = new File(this.track);
        AudioInputStream istream = null;
        try {
            istream = AudioSystem.getAudioInputStream(file);
        } catch (UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
        try {
            clip = AudioSystem.getClip();
            clip.open(istream);
            volumeC = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            clip.setFramePosition(0);
            clip.start();
        } catch (LineUnavailableException | IOException e)
        {
            e.printStackTrace();
        }

    }
    public void setVolume(float volume) {
        wt = Math.max(0, Math.min(volume, 1));
        float minVolume = volumeC.getMinimum();
        float maxVolume = volumeC.getMaximum();
        volumeC.setValue((maxVolume - minVolume)*(float)wt + minVolume);
    }


}
