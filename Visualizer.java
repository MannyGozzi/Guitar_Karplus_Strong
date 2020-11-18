public class Visualizer {

    private final int WIDTH = 100;
    private final int HEIGHT = 20;
    RingBuffer buffer = new RingBuffer(WIDTH);

    public Visualizer() {
        // Std draw is a static window which should be used only as a main window
        // if new instruments were added then we could make them popup windows by creating
        // new canvases by using the Draw class system (e.g. Draw canvas = new Draw())
         StdDraw.setCanvasSize(WIDTH,HEIGHT);
         StdDraw.setPenColor();
         StdDraw.setPenRadius(.005);
         StdDraw.enableDoubleBuffering();

    }

    public void draw(double sample) {
        StdDraw.clear();
        buffer.dequeue();
        buffer.enqueue(sample);
        for(int i = 0; i < buffer.size(); i+=buffer.size()/1) {
            StdDraw.point((double) i / buffer.size(), (double) buffer.get(i) + 0.5);
        }
        StdDraw.show();
    }
}
