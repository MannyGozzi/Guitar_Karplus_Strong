import com.sun.java.swing.plaf.windows.WindowsDesktopPaneUI;

/**
 * @author Manuel Gozzi
 * created on 2020/11/15
 *
 * A simple project that implements the Karplus Strong string algorithm.
 * Simply run the program and make something that sounds epic.
 * Note that you must click on the empty window in order to make music
 * since it needs window focus in order to capture the keys.
 *
 * stdlib.jar imported from https://introcs.cs.princeton.edu/java/stdlib/
 */

public class GuitarDriver {

    public static void main(String[] args) {
        int WIDTH = 1000;
        int HEIGHT = 300;
        Keys keys = new Keys();
        Visualizer visualizer = new Visualizer(WIDTH);
        MusicCanvas musicCanvas = new MusicCanvas(WIDTH,HEIGHT, 200, 0);
        musicCanvas.add(visualizer); // adds the visualizer to the music canvas window
        StdDraw.setCanvasSize(200, 200); // sets the size of the key capture window

        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {

                // collect the typed key
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                keys.pluckStrings(key);
            }

            // compute string samples
            double sample = keys.sampleStrings();

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            keys.ticStrings();

            // send the sample to the visualizer to update the waveform
            visualizer.updateSample(sample);
        }
    }

}




























