package AppButtons;

import audio.*;
import javax.sound.sampled.Mixer;
import javax.swing.*;
import java.awt.event.ActionListener;


public class RecButton extends AppButton {
    SoundRecorder recorder;
    JComboBox<Mixer.Info> mixers;
    public RecButton(SoundRecorder recorder, JComboBox<Mixer.Info> mixers) {
        super("Start record");
        this.recorder = recorder;
        this.mixers = mixers;
        addActionListener(e -> startRecord());
    }
    public void startRecord() {
        System.out.print("Recording");
        System.out.print(mixers.getSelectedItem());
        System.out.println("...");

        recorder.setMixerInfo((Mixer.Info) mixers.getSelectedItem());
        recorder.start();
    }
}
