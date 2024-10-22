package audio;

import javax.sound.midi.*;

import java.util.List;


public class MidiHandler {
    public MidiDevice device;
    public MidiDevice.Info[] infos = MidiSystem.getMidiDeviceInfo();
    List<Transmitter> transmitters;
    public MidiHandler() {
        for (MidiDevice.Info info : infos) {
            try {
                device = MidiSystem.getMidiDevice(info);
                System.out.println(info);
                //and for each transmitter
                for (Transmitter transmitter : device.getTransmitters()) {
                    //create a new receiver
                    transmitter.setReceiver(
                            //using my own audio.MidiInputReceiver
                            new MidiInputReceiver(device.getDeviceInfo().toString())
                    );
                }
                Transmitter trans = device.getTransmitter();
                trans.setReceiver(new MidiInputReceiver(device.getDeviceInfo().toString()));

                //open each device
                device.open();
                //if code gets this far without throwing an exception
                //print a success message
                System.out.println(device.getDeviceInfo() + " Was Opened");

            } catch (MidiUnavailableException e) {
                System.out.println("None");
            }
        }
    }
}
