import java.util.Vector;

/**
 * A collection of strings that makes updating
 * the string states easy. The strings are bound
 * to the keys on the keyboard by default.
 */

public class Keys {
    private Vector<GuitarString> strings;
    private String keyboard;

    /**
     * Sets up all the keys and their resepective frequencies.
     */
    public Keys() {
        strings = new Vector<>();
        keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/'";
        for(int i = 0; i < keyboard.length(); ++i) {
            // formula can be found here https://introcs.cs.princeton.edu/java/assignments/guitar.html
            strings.add(new GuitarString(440 * Math.pow(1.05956, i-24)));
        }
    }

    /**
     * Plucks all the strings which essentially
     * makes the strings in a state of white noise.
     *
     * @param key The key that was played
     */
    public void pluckStrings(char key) {
        int stringIndex = keyboard.indexOf(key);
        if(stringIndex >= 0) {
            strings.get(stringIndex).pluck();
        }
    }

    /**
     * Calls the tic method on all the strings
     * which advances their sound signature.
     */
    public void ticStrings() {
        for(GuitarString string : strings) {
            string.tic();
        }
    }

    /**
     * Provides the double value defined by the sum of the strings samples.
     * @return The sum of all of the string samples in the set of defined keys as a double.
     */
    public double sampleStrings() {
        double sum = 0;
        for(GuitarString string : strings) {
            sum += string.sample();
        }
        return sum;
    }
}
