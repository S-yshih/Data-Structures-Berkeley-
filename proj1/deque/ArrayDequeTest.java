package deque;

import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {

    @Test
    public void testingAsIGo(){
        ArrayDeque<Integer> list = new ArrayDeque<>();
        list.size();
        Boolean a = list.isEmpty();
        list.addFirst(1);
        list.addLast(2);
        a = list.isEmpty();
        int s = list.size();
        list.addFirst(0);
        list.addFirst(-1);
        list.printDeque();
        list.addLast(3);
        list.addFirst(-2);
        Integer b = list.get(0);
        Integer c = list.get(12);
        Integer d = list.get(7);
        Integer e = list.get(4);
        list.printDeque();
        list.removeFirst();
        list.removeLast();
        list.removeLast();
        list.printDeque();

        ArrayDeque<Integer> list2 = new ArrayDeque<>();
        list2.addFirst(-1);
        list2.addLast(0);
        list2.addLast(1);

        assertEquals(list, list2);
    }

    @Test
    public void addFirstResize(){
        ArrayDeque<Integer> list = new ArrayDeque<>();

        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addLast(7);
        list.addLast(8);
        list.addFirst(0);

        list.printDeque();
        System.out.println();
        System.out.println(list.size());

    }
    @Test
    public void addLastResize(){
        ArrayDeque<Integer> list = new ArrayDeque<>();

        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(4);
        list.addLast(5);
        list.addLast(6);
        list.addLast(7);
        list.addLast(8);
        list.addLast(9);

        list.printDeque();
        System.out.println();
        System.out.println(list.size());
    }
    @Test
    public void removeLastResize(){
        ArrayDeque<Integer> list = new ArrayDeque<>();

        for (int i = 0; i < 32; i ++){
            list.addLast(i);
        }
        for (int i = 4; i < 32; i++){
            list.removeLast();
        }

        list.printDeque();
        System.out.println();
        System.out.println(list.size());
    }

    @Test
    public void removeFirstResize(){
        ArrayDeque<Integer> list = new ArrayDeque<>();

        for (int i = 0; i < 32; i ++){
            list.addFirst(i);
        }
        for (int i = 4; i < 32; i++){
            list.removeFirst();
        }

        list.printDeque();
        System.out.println();
        System.out.println(list.size());
    }

    @Test
    public void removeFromEmpty(){
        ArrayDeque<Integer> list= new ArrayDeque<>();
        assertEquals(null, list.removeLast());
        assertEquals(null, list.removeFirst());
    }

    @Test
    public void add50Remove50(){
        ArrayDeque<Integer> list = new ArrayDeque<>();
        ArrayDeque<Integer> list2 = new ArrayDeque<>();

        for (int i = 0; i < 50; i ++){
            list.addLast(i);
        }
        for (int i = 0; i < 50; i++){
            list.removeFirst();
        }
        for (int i = 0; i < 50; i ++){
            list2.addFirst(i);
        }
        for (int i = 0; i < 50; i++){
            list2.removeFirst();
        }

        assertTrue(list.isEmpty());
        assertTrue(list2.isEmpty());
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String> lld1= new ArrayDeque<>();
        ArrayDeque<Double>  lld2 = new ArrayDeque<>();
        ArrayDeque<Boolean> lld3 = new ArrayDeque<>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }
}