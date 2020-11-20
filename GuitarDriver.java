import javax.swing.*;

/**
 * @author Manuel Gozzi
 * created on 2020/11/15
 *
 * stdlib.jar imported from https://introcs.cs.princeton.edu/java/stdlib/
 */

public class GuitarDriver {

    public static void main(String[] args) {

        // Create two guitar strings, for concert A and C
        // octave 4
        Octave octave = new Octave();
        Visualizer visualizer = new Visualizer(1000);
        JFrame canvas = new JFrame();
        canvas.setLocation(200,0);
        StdDraw.setCanvasSize(200, 200);
        canvas.setSize(1000, 300);
        canvas.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas.setVisible(true);
        canvas.add(visualizer);


        // the main input loop
        while (true) {

            // check if the user has typed a key, and, if so, process it
            if (StdDraw.hasNextKeyTyped()) {
 
                // the user types this character
                char key = StdDraw.nextKeyTyped();

                // pluck the corresponding string
                octave.pluckStrings(key);
            }

            // compute the superposition of the samples
            double sample = octave.sampleStrings();

            // send the result to standard audio
            StdAudio.play(sample);

            // advance the simulation of each guitar string by one step
            octave.ticStrings();
            visualizer.repaint();
            visualizer.updateSample(sample);
        }
    }

}




























