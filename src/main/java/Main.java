import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import javax.swing.text.BadLocationException;

public class Main {

    public static AudioFile audio = new AudioFile("test.wav", 1.0);
    public static AudioFormat wav_format = new AudioFormat(44100, 16, 2, true, true);
    public static SoundRecorder recorder = new SoundRecorder(wav_format);
    public static Mixer.Info[] infos = getAccessMixers();

    public static JFrame frame = new JFrame("MyWindow");
    public static JButton rec_button = new JButton("Record");
    public static JButton stoprec_button = new JButton("Stop");
    public static JButton amp_button = new JButton("set amplifying");
    public static Integer[] amps = getAmps();
    public static JComboBox<Integer> amp_text = new JComboBox<Integer>(amps);
    public static JButton save_button = new JButton("Save");
    public static JTextArea text_field = new JTextArea();
    public static Font font = Font.getFont("Arial");

    public static JComboBox<Mixer.Info> mixerList = new JComboBox<Mixer.Info>(infos);
    public static JPanel panel = new JPanel(new GridLayout(4, 4, 4, 4));
    public static Integer[] getAmps() {
        var amps = new Integer[5];
        for (var i = 1; i - 1 < amps.length; i++) {
            amps[i - 1] = i;
        }
        return amps;
    }
    public static Mixer.Info[] getAccessMixers() {
        Mixer.Info[] infos = AudioSystem.getMixerInfo();
        ArrayList<Mixer.Info> infos_arr = new ArrayList<Mixer.Info>();
        for (Mixer.Info info : infos) {
            try {
                AudioSystem.getTargetDataLine(wav_format, info);
                infos_arr.add(info);
            } catch (Exception e) {}

        }
        Mixer.Info[] mixers = new Mixer.Info[infos_arr.size()];
        for (var i = 0; i < mixers.length; i++)
        {
            mixers[i] = infos_arr.get(i);
        }
        return mixers.clone();
    }

    public static void startRecord() {
        System.out.println("Start recording ....");
        System.out.println(mixerList.getSelectedItem());

        recorder.setMixerInfo((Mixer.Info) mixerList.getSelectedItem());
        recorder.start();
    }
    public static void stopRecord() {
        recorder.stop();
    }
    public static void saveCurrentRecord(String record_name) {
        if (!WaveDataUtil.saveToFile(record_name, recorder.getAudioInputStream()))
        {
            System.out.println("SaveError");
        }
    }
    public static void setAmpValue() {
        recorder.setAmplify((Integer) amp_text.getSelectedItem());
    }

    public static void main(String[] args) {

        text_field.setFont(font);
        text_field.setPreferredSize(new Dimension(60, 25));
        rec_button.setPreferredSize(new Dimension(60, 25));

        stoprec_button.setPreferredSize(new Dimension(60, 25));

        save_button.setPreferredSize(new Dimension(60, 25));
        text_field.setLineWrap(true);
        rec_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startRecord();
            }
        });
        stoprec_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopRecord();
            }
        });
        save_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCurrentRecord(text_field.getText());
            }
        });
        amp_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel.add(mixerList);
        panel.add(rec_button);
        panel.add(text_field);
        panel.add(amp_button);
        panel.add(amp_text);
        panel.add(stoprec_button);
        panel.add(save_button);

        frame.setSize(1200, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.pack();
        frame.setVisible(true);


    }
}

