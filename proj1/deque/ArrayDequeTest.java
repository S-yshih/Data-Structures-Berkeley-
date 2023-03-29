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
        list.addFirst(0);
        list.addFirst(-1);
        list.addLast(3);
    }

}