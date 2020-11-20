import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Visualizer extends JPanel {

    RingBuffer buffer;

    /**
     * @param width The width of the parent window
     */
    public Visualizer(int width) {
        // Std draw is a static window which should be used only as a main window
        // if new instruments were added then we could make them popup windows by creating
        // new canvases by using the Draw class system (e.g. Draw canvas = new Draw())
        buffer = new RingBuffer(1000);
        this.setBackground(Color.DARK_GRAY);
    }

    /**
     * Paints the waveform over time using the data supplied from the updateSample method
     * @param g the graphics object supplied by the JFrame parent or other component
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;                                // the supplied Graphics object does not support altering the stroke or antialiasing so we can cast it to Graphics2D which does support it
        super.paintComponent(g2D);                                      // paints the canvas configuration (e.g. background color white in this case), essentially clearing the canvas
        int WIDTH = SwingUtilities.getRoot(this).getWidth();        //  gets the width of the parent window
        int HEIGHT = SwingUtilities.getRoot(this).getHeight();      //  "      " height "                  "

        RenderingHints hints = new RenderingHints(                     // enables antialiasing to smooth out the lines draw between the waveform points
                RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2D.setRenderingHints(hints);
        g2D.setStroke(new BasicStroke(3.0f));                     // sets the stroke to be thicker

        g2D.setColor(Color.CYAN);
        for(int i = 0; i < buffer.size() - 1; i++) {
           g2D.drawLine(i, (int) Math.ceil(buffer.get(i) * HEIGHT) + HEIGHT/2, i + 1, (int) Math.ceil(buffer.get(i + 1) * HEIGHT) + HEIGHT/2);
        }
    }

    public void updateSample(double sample) {
        buffer.dequeue();
        buffer.enqueue(sample);
        repaint();
    }
}


























