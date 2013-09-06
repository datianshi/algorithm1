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

    public Item[] getItems() {
        return q;
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
        if(item == null){
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
        if (head < tail) {
            int index = 0;
            for (int i = head; i < tail; i++) {
                copy[index] = q[i];
                index++;
            }
            tail = q.length;
        } else {
            int index = 0;
            for (int i = head; i < q.length; i++) {
                copy[index] = q[i];
                index++;
            }
            for (int i = 0; i < (q.length + tail - head); i++) {
                copy[index] = q[i];
                index++;
            }
        }

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
        Item item = q[head];
        q[head] = null;
        head++;
        return item;
    }
    
    public Item dequeueRandom() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        if (size() == q.length / 4) {
            shrink(q.length / 4);
        }
        if (head == q.length) {
            head = 0;
        }
        Item item = q[head];
        q[head] = null;
        head++;
        return item;
    }    

    public Item sample() {
        if (size() == 0) {
            throw new NoSuchElementException();
        }
        return null;
    }

    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }
    
    private int randomIndex(){
        int random = StdRandom.uniform(size());
        int index = random + head;
        if(index < q.length){
            return index;
        }
        else{
            return index - q.length;
        }
        
    }

    private class RandomizedIterator implements Iterator<Item> {

        private int current = head;

        public boolean hasNext() {
            if(current == tail){
                return false;
            }
            else if(current == q.length){
                if(tail == 0){
                    return false;
                }
                current = 0;
            }
            return true;
        }

        public Item next() {
            if(current >= q.length || q[current] == null){
                throw new NoSuchElementException();
            }
            Item item = q[current];
            current++;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
