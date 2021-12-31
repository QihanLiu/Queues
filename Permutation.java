import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> randarray = new RandomizedQueue<String>();
        if (StdIn.isEmpty()) {
            throw new NullPointerException("Empty file!");
        }
        int i = 0;
        for (i = 0; i < k; i++) {
            randarray.enqueue(StdIn.readString());
        }
        String temp;
        while (!StdIn.isEmpty()) {
            i++;
            temp = StdIn.readString();
            if (StdRandom.bernoulli(1.0 * k / i)) {
                randarray.dequeue();
                randarray.enqueue(temp);
            }
        }
        Iterator<String> it = randarray.iterator();
        while (it.hasNext()) {
            StdOut.println(it.next());
        }

    }
}