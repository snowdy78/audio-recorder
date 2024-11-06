package audio;

import java.io.*;
import javax.sound.sampled.*;

public class SoundRecorder implements Runnable {
    private AudioInputStream audioInputStream;
    private AudioFormat format;
    private boolean is_recording = false;
    public Thread thread;
    private double duration;
    private float gain = 0.f;
    private float volume = 0.f;
    private Mixer.Info info = null;
    public SoundRecorder(AudioFormat format) {

        this.format = format;
    }

    public void start() {
        if (!is_recording) {
            thread = new Thread(this);
            thread.setName("Capture Microphone");
            is_recording = true;
            thread.start();
            System.out.println("Started");
            return;
        }
        System.out.println("Start Error");
    }

    public void stop() {
        if (is_recording) {
            is_recording = false;
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        is_recording = false;
        thread = null;
        System.out.println("Stopped");

    }

    @Override
    public void run() {
        duration = 0;
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final TargetDataLine line = getTargetDataLineForRecord();
        var gain_control = (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);
        var volume_control = (FloatControl) line.getControl(FloatControl.Type.VOLUME);
        gain_control.setValue(gain); // throws errror IllegalArgumentException
        volume_control.setValue(volume);
        if (line != null) {
            try {
                int frameSizeInBytes = format.getFrameSize();
                int bufferLengthInFrames = line.getBufferSize() / 8;
                final int bufferLengthInBytes = bufferLengthInFrames * frameSizeInBytes;
                buildByteOutputStream(out, line, bufferLengthInBytes);
                setAudioInputStream(convertToAudioIStream(out, frameSizeInBytes));
                audioInputStream.reset();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void buildByteOutputStream(final ByteArrayOutputStream out, final TargetDataLine line, final int bufferLengthInBytes) throws IOException {
        final byte[] data = new byte[bufferLengthInBytes];
        line.start();
        while (is_recording) {
            int numBytesRead = line.read(data, 0, bufferLengthInBytes);
            if (numBytesRead == -1) {
                break;
            }
            out.write(data, 0, numBytesRead);
        }
    }
    public float getGain() {
        return gain;
    }
    public void setGain(float new_gain) {
        gain = new_gain;
    }
    public float getVolume() {
        return volume;
    }
    public void setVolume(float new_volume) {
        new_volume = new_volume;
    }
    private void setAudioInputStream(AudioInputStream aStream) {
        this.audioInputStream = aStream;
    }
    public AudioInputStream convertToAudioIStream(final ByteArrayOutputStream out, int frameSizeInBytes) {
        final byte[] buff = out.toByteArray();
        ByteArrayInputStream bais = new ByteArrayInputStream(buff);
        AudioInputStream audioStream = new AudioInputStream(bais, format, buff.length / frameSizeInBytes);
        long milliseconds = (long) ((audioStream.getFrameLength() * 1000) / format.getFrameRate());
        duration = milliseconds / 1000.0;
        System.out.println("duration: " + duration);
        return audioStream;
    }
    public Mixer.Info getMixerInfo() {
        return info;
    }
    public void setMixerInfo(Mixer.Info info) {
        this.info = info;
    }
    public TargetDataLine getTargetDataLineForRecord() {
        TargetDataLine line;
        try {
            line = AudioSystem.getTargetDataLine(format, info);
            System.out.println("data: " + info.getName());
            line.open(format, line.getBufferSize());
        } catch (final Exception ex) {
            System.out.println("getTargetDataLine excepted");
            return null;
        }
        return line;
    }

    public AudioInputStream getAudioInputStream() {
        return audioInputStream;
    }

    public AudioFormat getFormat() {
        return format;
    }
    public boolean isRecording() {
        return is_recording;
    }

    public void setFormat(AudioFormat format) {
        this.format = format;
    }

    public Thread getThread() {
        return thread;
    }

    public double getDuration() {
        return duration;
    }
}