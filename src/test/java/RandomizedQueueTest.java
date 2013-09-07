import junit.framework.Assert;

import org.junit.Test;

public class RandomizedQueueTest {
    @Test
    public void test() {
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        Assert.assertEquals(0, queue.size());
        System.out.println("enqueue 5");
        queue.enqueue("5");
        print(queue);
        // testCorrect(queue, array("5"));
        Assert.assertEquals(1, queue.size());
        System.out.println("dequeue 5");
        Assert.assertEquals("5", queue.dequeue());
        //
        // testCorrect(queue, array());
        //
        // Assert.assertEquals(0, queue.size());
        //
        System.out.println("enqueue 5");
        queue.enqueue("5");
        System.out.println("enqueue 10");
        queue.enqueue("10");
        System.out.println("enqueue 20");
        queue.enqueue("20");

        print(queue);
        //
        // testCorrect(queue, array("5", "10", "20"));
        //
        Assert.assertEquals(3, queue.size());
        System.out.println("deque: " + queue.dequeue());
        // Assert.assertEquals(2, queue.size());
        System.out.println("deque: " + queue.dequeue());
        Assert.assertEquals(1, queue.size());
        System.out.println("enqueue 5");
        queue.enqueue("5");
        Assert.assertEquals(2, queue.size());
        // testCorrect(queue, array("20", "5"));
        System.out.println("enqueue 6");
        queue.enqueue("6");
        Assert.assertEquals(3, queue.size());

        print(queue);
        //
        // testCorrect(queue, array("20", "5", "6"));
        //
        System.out.println("deque: " + queue.dequeue());
        System.out.println("deque: " + queue.dequeue());
        Assert.assertEquals(1, queue.size());
        System.out.println("deque: " + queue.dequeue());
        Assert.assertEquals(0, queue.size());
        print(queue);

        System.out.println("enqueue 5");
        queue.enqueue("5");
        System.out.println("enqueue 6");
        queue.enqueue("6");
        System.out.println("enqueue 7");
        queue.enqueue("7");
        System.out.println("enqueue 8");
        queue.enqueue("8");
        Assert.assertEquals(4, queue.size());

        System.out.println("deque: " + queue.dequeue());
        System.out.println("deque: " + queue.dequeue());

        System.out.println("enqueue 9");
        queue.enqueue("9");
        System.out.println("enqueue 10");
        queue.enqueue("10");
        System.out.println("deque: " + queue.dequeue());

        print(queue);

    }
    
    @Test
    public void equeueFour(){
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
    }
    
    @Test
    public void equeueNine(){
        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");
        queue.enqueue("F");
        queue.enqueue("G");
        queue.enqueue("H");
        queue.enqueue("I");
        for(int i=0 ;i< 9; i++){
            System.out.println(queue.dequeue());
        }
    }    

    private void print(RandomizedQueue<String> q) {
        System.out.print("The queue is: ");
        for (String s : q) {
            System.out.print(s + " ");
        }
        System.out.println();
    }

    private void testCorrect(RegularQueue<String> queue, String[] array) {
        int i = 0;
        for (String q : queue) {
            Assert.assertEquals(q, array[i]);
            i++;
        }
    }

    private String[] array(String... args) {
        return args;
    }

}
