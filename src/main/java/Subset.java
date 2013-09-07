public class Subset {

    public static void main(String[] args) {
        int k = Integer.valueOf(args[0]);
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        String x = null;
        while (!StdIn.isEmpty()) {
            x = StdIn.readString();
            queue.enqueue(x);
        }
    
        for (int i = 0; i < k; i++) {
            StdOut.println(queue.dequeue());
        }

    }
}
