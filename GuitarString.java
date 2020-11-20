/**
 * @author Manuel Gozzi
 * created on 2020/11/15
 *
 * Simulates a guitar string using the Karplus Strong algorithm.
 * The method takes the average of the first two values in a ring buffer
 * multiplied by the decay factor and enqueues it into the cyclical ring
 * buffer which decays and diffuses the sound. This creates a sound very
 * similar to a guitar and has a performance of O(n) since the computation
 * is linear.
 */

public class GuitarString {

    private RingBuffer samples;
    private final double SAMPLING_RATE = 44_100.0;
    private final double ENERGY_DECAY_FACTOR = .994;
    private int ticCount; // note, never used

    /**
     *     Creates a guitar string of the specified frequency,
     *     using a sampling rate of 44,100hz
      */
    public GuitarString(double frequency) {
        samples = new RingBuffer((int) Math.ceil(SAMPLING_RATE / frequency));
        ticCount = 0;
    }

    /**
     *     Creates a guitar string whose size and initial values are given by
     *     the specified array
      */
    public GuitarString(double[] init) {
        samples = new RingBuffer(init.length);
        for(double num : init) {
            samples.enqueue(num);
        }
        ticCount = 0;
    }

    /**
     *  Plucks the guitar string (by replacing the buffer with white noise).
     *  This is the start of the sound
     */
    public void pluck() {
        samples.clear();
        while(!samples.isFull()) {
            samples.enqueue(Math.random() - 0.5);
        }
    }

    /**
     * advances the Karplus-Strong simulation one time step
     * adds the average of the first two samples multiplied by the energy decay
     * factor (.994) to the ringbuffer and dequeues the first sample
     * this effectively acts as a lowpass filter while also dampening the sound
     * as the virtual string vibrates across the medium
      */
    public void tic() {
        double firstSample = samples.dequeue();
        samples.enqueue((firstSample + samples.peek())/2 * ENERGY_DECAY_FACTOR);
        ++ticCount;
    }

    /**
     * @return The current sample from the string as a double
     */
    public double sample() {
        if(!samples.isEmpty()) return samples.peek();
        return 0.0;
    }

}
