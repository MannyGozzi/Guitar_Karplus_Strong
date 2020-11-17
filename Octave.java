import java.util.Vector;

public class Octave {
    private int octave;
    private Vector<GuitarString> strings;
    private String keyboard;

    public Octave() {
        octave = 4;
        strings = new Vector<>();
        keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        for(int i = 0; i < keyboard.length(); ++i) {
            strings.add(new GuitarString(440 * Math.pow(1.05956, i-24)));
        }
    }

    public void pluckStrings(char key) {
        int stringIndex = keyboard.indexOf(key);
        if(stringIndex >= 0) {
            strings.get(stringIndex).pluck();
        }
    }

    public void ticStrings() {
        for(GuitarString string : strings) {
            string.tic();
        }
    }

    public double sampleStrings() {
        double sum = 0;
        for(GuitarString string : strings) {
            sum += string.sample();
        }
        return sum;
    }

    public Vector<GuitarString> getStrings() {
        return strings;
    }
}
