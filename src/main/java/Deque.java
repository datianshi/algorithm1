import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    // construct an empty deque
    private Node first = null;
    private Node last = null;
    private int count = 0;

    public Deque() {

    }

    private class Node {
        Item item;
        Node next;
        Node prev;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return count == 0;
    }

    // return the number of items on the deque
    public int size() {
        return count;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        Node n = new Node();
        n.item = item;
        if (first == null) {
            first = n;
            last = n;
            first.next = last;
            last.prev = first;
        } else {
            Node oldFirst = first;
            first = n;
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        count ++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        Node n = new Node();
        n.item = item;
        if (last == null) {
            first = n;
            last = n;
        } else {
            Node oldLast = last;
            last = n;
            oldLast.next = last;
            last.prev = oldLast;
        }
        count ++;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        if(count == 1){
            Item item = first.item;
            first = null;
            last = null;
            count = 0;
            return item;
        }
        Item item = first.item;
        first = first.next;
        first.prev = null;
        count--;
        return item;
    }

    // delete and return the item at the end
    public Item removeLast() {
        if(count == 1){
            Item item = first.item;
            first = null;
            last = null;
            count = 0;
            return item;
        }
        Item item = last.item;
        last = last.prev;
        last.next = null;
        count--;
        return item;
    }

    private class DequeueIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {

        }

    }

    public Iterator<Item> iterator() {
        return new DequeueIterator();
    }
}
