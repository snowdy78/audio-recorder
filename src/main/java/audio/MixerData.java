package audio;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;
import java.util.ArrayList;

public class MixerData {
    Mixer.Info[] mixers;
    public MixerData(AudioFormat format) {
        Mixer.Info[] infos = AudioSystem.getMixerInfo();
        ArrayList<Mixer.Info> infos_arr = new ArrayList<Mixer.Info>();
        for (Mixer.Info info : infos) {
            try {
                AudioSystem.getTargetDataLine(format, info);
                infos_arr.add(info);
            } catch (Exception e) {}
        }
        this.mixers = new Mixer.Info[infos_arr.size()];
        for (var i = 0; i < mixers.length; i++)
        {
            mixers[i] = infos_arr.get(i);
        }
    }
    public Mixer.Info[] getMixers() {
        return mixers;
    }
}
