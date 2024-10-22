package audio;

import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class WaveDataUtil {
    public static boolean saveToFile(String name, AudioInputStream audioInputStream) {
        var fileType = AudioFileFormat.Type.WAVE;
        System.out.println("Saving...");
        if (name == null || audioInputStream == null) {
            System.out.println(name + " " + fileType + " " + audioInputStream);
            return false;
        }
        File myFile = new File(name + "." + fileType.getExtension());
        try {
            audioInputStream.reset();
        } catch (Exception e) {
            System.out.println("ResetError");
            return false;
        }
        int i = 0;
        while (myFile.exists()) {
            String temp = "" + i + myFile.getName();
            myFile = new File(temp);
        }
        try {
            AudioSystem.write(audioInputStream, fileType, myFile);
        } catch (Exception ex) {
            System.out.println("InaccessibleFileError");
            return false;
        }
        System.out.println("Saved " + myFile.getAbsolutePath());
        return true;
    }
}