/**
 * @author Manuel Gozzi
 * created on 2020/11/15
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

    // tests and calls every instance method in this class
    public static void main(String[] args) {
        RingBuffer test = new RingBuffer(5);
        test.enqueue(1.0);
        test.enqueue(2.0);
        test.enqueue(3.0);
        test.enqueue(4.0);
        test.enqueue(5.0);
        test.dequeue();
        test.enqueue(5.0);
        test.dequeue();
        test.enqueue(5.0);
        test.dequeue();
        test.enqueue(5.0);
        test.dequeue();
        test.enqueue(5.0);
        test.dequeue();
        test.enqueue(5.0);
        test.dequeue();
        test.enqueue(5.0);
        test.dequeue();
        test.enqueue(5.0);
    }

}
