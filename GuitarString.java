/**
 * @author Manuel Gozzi
 * created on 2020/11/15
 */

public class GuitarString {
    // YOUR INSTANCE VARIABLES HERE
    private RingBuffer samples;
    private final double SAMPLING_RATE = 44_100.0;
    private final double ENERGY_DECAY_FACTOR = .994;
    private int ticCount;

    // creates a guitar string of the specified frequency,
    // using sampling rate of 44,100
    public GuitarString(double frequency) {
        samples = new RingBuffer((int) Math.ceil(SAMPLING_RATE / frequency));
        ticCount = 0;
    }

    // creates a guitar string whose size and initial values are given by
    // the specified array
    public GuitarString(double[] init) {
        samples = new RingBuffer(init.length);
        for(double num : init) {
            samples.enqueue(num);
        }
        ticCount = 0;
    }

    // returns the number of samples in the ring buffer
    public int length() {
        return ticCount;
    }

    // plucks the guitar string (by replacing the buffer with white noise)
    public void pluck() {
        samples.clear();
        while(!samples.isFull()) {
            samples.enqueue(Math.random() - 0.5);
        }
    }

    public RingBuffer getSamples() {
        return samples;
    }

    // advances the Karplus-Strong simulation one time step
    // adds the average of the first two samples multiplied by the energy decay
    // factor (.994) in the ringbuffer and dequeues the first sample
    // this effectively acts as a lowpass filter while also dampening the sound
    // as the virtual string vibrates across the medium
    public void tic() {
        double firstSample = samples.dequeue();
        samples.enqueue((firstSample + samples.peek())/2 * ENERGY_DECAY_FACTOR);
        ++ticCount;
    }

    // returns the current sample
    public double sample() {
        if(!samples.isEmpty()) return samples.peek();
        return 0.0;
    }

    public int getSize() {
        return samples.size();
    }


    // tests and calls every constructor and instance method in this class
    public static void main(String[] args) {
        double[] samples = {1.0, 2.0, 3.0, 4.0, 5.0};
        GuitarString string = new GuitarString(samples);
        System.out.println(string.getSamples());
        string.tic();
        System.out.println(string.getSamples());
        string.tic();
        System.out.println(string.getSamples());
        string.tic();
        System.out.println(string.getSamples());
        string.tic();
        System.out.println(string.getSamples());
        string.tic();
        System.out.println(string.getSamples());
        string.tic();
        System.out.println(string.getSamples());

    }

}
