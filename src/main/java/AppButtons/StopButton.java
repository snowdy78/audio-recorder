package AppButtons;

import audio.*;
import javax.sound.sampled.Mixer;
import javax.swing.*;
import java.awt.event.ActionListener;


public class StopButton extends AppButton {
    SoundRecorder recorder;
    public StopButton(SoundRecorder recorder) {
        super("Stop record");
        this.recorder = recorder;
        addActionListener(e -> stopRecord());
    }
    public void stopRecord() {
        System.out.println("Stopped!");
        recorder.stop();
    }
}
