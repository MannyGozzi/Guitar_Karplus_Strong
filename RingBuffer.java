/**
 * @author Manuel Gozzi
 * created on 2020/11/15
 *
 * This RingBuffer holds an array of doubles
 * Elements are added in a ring formation and
 * when elements are dequeued new elements will
 * take their place. The first element of the
 * ring buffer doesn't necessarily start at 0.
 * This creates a highly efficient data structure
 * for rapidly altering data that maintains its inital size.
 * This class could be defined as a generic however it would
 * require compiler flags which are unecessary for this project.
 */

public class RingBuffer {

    private double[] array;
    private int first, last;
    private int size;

    // creates an empty ring buffer with the specified capacity
    public RingBuffer(int capacity) {
        array = new double[capacity];
        first = 0;
        last = 0;
        size = capacity;
    }

    // return the capacity of this ring buffer
    public int capacity() {
        return array.length;
    }

    // return number of items currently in this ring buffer
    public int size() {
        return size;
    }

    public double get(int index) {
        return array[index];
    }

    // is this ring buffer empty (size equals zero)?
    public boolean isEmpty() {
        return size == 0;
    }

    // is this ring buffer full (size equals capacity)?
    public boolean isFull() {
        return size == array.length;
    }

    // adds item x to the end of this ring buffer
    public void enqueue(double x) throws IllegalStateException {
        last = wrap(last);
        if(!isFull()) {
            array[last++] = x;
            ++size;
        } else {
            throw new IllegalStateException();
        }
    }

    // returns the wrapped value of the supplied index in regards to the array's size
    // e.g. if index = 7 and the array has a size of 7 then it will be wrapped to 0
    private int wrap(int index) {
        if (index == array.length) {
            return 0;
        }
        return index;
    }

    // deletes and returns the item at the front of this ring buffer
    public double dequeue() throws IllegalStateException {
        first = wrap(first);
        if(isEmpty()) {
            throw new IllegalStateException();
        } else {
            --size;
            return array[first++];
        }
    }

    // returns the item at the front of this ring buffer
    public double peek() {
        first = wrap(first);
        return array[first];
    }

    public void clear() {
        first = 0;
        last = 0;
        size = 0;
    }

    public String toString() {
        String str = "";
        for(double num : array) {
            str = str + " " + num;
        }
        return str;
    }

}
