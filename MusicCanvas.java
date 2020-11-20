import javax.swing.*;

/**
 * This class was made to clean up the main driver. It just
 * configures standard stuff such as the window size, title,
 * background color, etc.
 */

public class MusicCanvas extends JFrame {
    public MusicCanvas(int width, int height, int offsetX, int offsetY) {
        super();
        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setLocation(offsetX,offsetY);
        this.setTitle("Guitar Karplus Strings Visualizer");
    }
}
