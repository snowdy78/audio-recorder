import java.io.ByteArrayOutputStream;

public class Amplifier {
    private byte[] data;
    public Amplifier(ByteArrayOutputStream out_stream) {
        data = out_stream.toByteArray();
    }
    void amp_volume(int i) {
        for (var b : data) {
            b *= i;
        }
    }
    byte[] getByteArray() {
        return data;
    }
}
