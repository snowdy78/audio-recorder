package audio;

import javax.sound.midi.MidiMessage;
import javax.sound.midi.Receiver;

public class MidiInputReceiver implements Receiver {
    public String name;
    public MidiInputReceiver(String name) {
        this.name = name;
    }
    public void send(MidiMessage msg, long timeStamp) {
        System.out.println("midi received");
    }
    public void close() {}
}

