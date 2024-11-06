
import audio.*;
import AppButtons.*;
import javax.sound.sampled.*;
import java.awt.*;
import javax.swing.*;

public class Main {

    public static final AudioFormat wav_format = new AudioFormat(44100, 16, 2, true, true);
    public static final SoundRecorder recorder = new SoundRecorder(wav_format);
    public static final Mixer.Info[] infos = new MixerData(wav_format).getMixers().clone();
    public static final JComboBox<Mixer.Info> mixerList = new JComboBox<>(infos);
    public static final Font font = Font.getFont("Arial");
    public static final JTextArea text_field = new JTextArea();
    public static final Integer[] amps = getAmps();
    public static final JComboBox<Integer> amp_text = new JComboBox<>(amps);
    public static final JFrame frame = new JFrame("MyWindow");

    // Buttons
    public static AppButton[] buttons = new AppButton[] {
            new RecButton(recorder, mixerList),
            new StopButton(recorder),
            new AmplifierButton(recorder),
            new SaveButton(recorder, () -> text_field.getText()),
    };
    public static JPanel panel = new JPanel(new GridLayout(4, 4, 4, 4));

    public static Integer[] getAmps() {
        var amps = new Integer[5];
        for (var i = 1; i - 1 < amps.length; i++) {
            amps[i - 1] = i;
        }
        return amps;
    }

    public static void main(String[] args) {

        text_field.setFont(font);
        text_field.setLineWrap(true);
        text_field.setPreferredSize(new Dimension(60, 25));
        
        Component[] components = new Component[] {
                mixerList,
                text_field,
                amp_text
        };
        for (var i = 0; i < Math.max(components.length, buttons.length); i++) {
            if (i < components.length) {
                panel.add(components[i]);
            }
            if (i < buttons.length) {
                panel.add(buttons[i]);
            }
        }
        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);


    }
}

