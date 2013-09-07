import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] q;
    private int head = 0;
    private int tail = 0;
    private int n = 1;

    public RandomizedQueue() {
        q = (Item[]) new Object[1];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        if (tail > head) {
            return tail - head;
        } else if (tail == head) {
            if (tail < q.length && q[tail] != null) {
                return q.length;
            } else {
                return 0;
            }
        } else {
            return tail + q.length - head;
        }
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size() == q.length)
            enlarge(2 * q.length);
        resetPointer();
        q[tail] = item;
        tail++;
    }

    private void resetPointer() {
        if (tail == q.length) {
            tail = 0;
        }
        if (head == q.length) {
            head = 0;
        }
    }

    private void enlarge(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];

        for (int i = 0; i < q.length; i++) {
            copy[i] = q[i];
        }
        head = 0;
        tail = q.length;
        q = copy;
    }

    private void shrink(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        copyArray(copy);
        tail = capacity;
        head = 0;
        q = copy;
    }

    public Item dequeue() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        if (size() == q.length / 4) {
            shrink(q.length / 4);
        }
        if (head == q.length) {
            head = 0;
        }

        int deletedIndex = randomIndex();
        Item item = q[deletedIndex];
        q[deletedIndex] = q[head];
        q[head] = null;
        head++;
        return item;
    }

    public Item sample() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return q[randomIndex()];
    }

    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private int randomIndex() {
        int random = StdRandom.uniform(size());
        int index = random + head;
        if (index < q.length) {
            return index;
        } else {
            return index - q.length;
        }

    }

    private void copyArray(Item[] copy) {
        int index = 0;
        if (head < tail) {
            for (int i = head; i < tail; i++) {
                copy[index] = q[i];
                index++;
            }
        } else {
            for (int i = head; i < q.length; i++) {
                copy[index] = q[i];
                index++;
            }
            for (int i = 0; i < tail; i++) {
                copy[index] = q[i];
                index++;
            }
        }
    }

    private class RandomizedIterator implements Iterator<Item> {
        private Item[] items;
        private int current = 0;

        private RandomizedIterator() {
            int size = size();
            items = (Item[]) new Object[size];
            if (size > 0) {
                copyArray(items);
                StdRandom.shuffle(items);
            }
        }

        public boolean hasNext() {
            return current != items.length;
        }

        public Item next() {
            if (current == items.length) {
                throw new NoSuchElementException();
            }
            Item item = items[current];
            current++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
