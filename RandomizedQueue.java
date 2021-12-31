import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private int N;
    private Item[] randarray;

    // construct an empty randomized queue
    public RandomizedQueue() {
        randarray = (Item[]) new Object[2];
        N = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return N == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return N;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new IllegalArgumentException("Object to add is NULL!");

        if (N == randarray.length) {
            resize(2 * randarray.length);
        }
        randarray[N++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        if (N == 0)
            throw new NoSuchElementException("Queque is Empty!");

        int index = StdRandom.uniform(N);
        Item item = randarray[index];
        randarray[index] = randarray[N - 1];
        randarray[N - 1] = null;
        N--;
        if (N > 0 && N == randarray.length / 4)
            resize(randarray.length / 2);
        return item;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = randarray[i];
        }
        randarray = copy;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (N == 0)
            throw new NoSuchElementException("Queque is Empty!");

        int i = StdRandom.uniform(N);
        return randarray[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomIterator();
    }

    private class RandomIterator implements Iterator<Item> {
        private Item[] copyarray;
        private int ind;

        public RandomIterator() {
            copyque();
            ind = 0;
        }

        public boolean hasNext() {
            return ind < N;
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copyarray[ind++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        private void copyque() {
            copyarray = (Item[]) new Object[N];
            for (int i = 0; i < N; i++) {
                copyarray[i] = randarray[i];
            }
            StdRandom.shuffle(copyarray);
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> intrandque = new RandomizedQueue<Integer>();
        intrandque.enqueue(1);
        intrandque.enqueue(2);
        intrandque.enqueue(3);
        intrandque.enqueue(4);
        Iterator<Integer> it = intrandque.iterator();

        StdOut.println("Print: ");
        while (it.hasNext()) {
            StdOut.println(it.next());
        }

        StdOut.println("Sample: " + intrandque.sample());
        StdOut.println("Remove temp: " + intrandque.dequeue());
        StdOut.println("Remove temp: " + intrandque.dequeue());

        it = intrandque.iterator();
        while (it.hasNext()) {
            StdOut.println(it.next());
        }
    }

}