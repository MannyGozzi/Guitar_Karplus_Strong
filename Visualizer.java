import org.jetbrains.annotations.NotNull;

import java.util.Vector;

public class Visualizer {

    Draw canvas;
    private final int WIDTH = 1000;
    private final int HEIGHT = 300;
    RingBuffer buffer = new RingBuffer(WIDTH);

    public Visualizer() {
         canvas = new Draw();
         canvas.setCanvasSize(WIDTH,HEIGHT);
         canvas.setPenColor();
         canvas.setPenRadius();
    }

    public void draw(double sample) {
        buffer.dequeue();
        buffer.enqueue(sample);
        for(int i = 0; i < buffer.size(); ++i) {
            canvas.point(i * WIDTH/buffer.size(), buffer.get(i) * HEIGHT);
        }
        System.out.println("something");
    }
}
