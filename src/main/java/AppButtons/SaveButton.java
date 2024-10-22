package AppButtons;

import audio.SoundRecorder;
import audio.WaveDataUtil;

import java.awt.event.ActionListener;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class SaveButton extends AppButton {
    private SoundRecorder recorder = null;
    private Supplier<String> getFileName = () -> "";
    public SaveButton(SoundRecorder recorder, Supplier<String> getFileName) {
        super("Save");
        this.recorder = recorder;
        this.getFileName = getFileName;
        addActionListener(e -> saveCurrentRecord(this.getFileName.get()));
    }
    void saveCurrentRecord(String record_name) {
        if (!WaveDataUtil.saveToFile(record_name, recorder.getAudioInputStream()))
        {
            System.out.println("SaveError");
        }
    }
}
