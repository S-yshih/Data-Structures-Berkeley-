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
    }

}