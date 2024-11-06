package AppButtons;

import java.awt.event.ActionListener;
import audio.SoundRecorder;

public class AmplifierButton extends AppButton {
    private SoundRecorder recorder = null;
    public AmplifierButton(SoundRecorder recorder) {
        super("Set amplifying");
        this.recorder = recorder;
        addActionListener(e -> amplify(1.0f));
    }
    public void amplify(float x) {
        if (recorder != null) {
            recorder.setGain(x);
        }
    }
}
