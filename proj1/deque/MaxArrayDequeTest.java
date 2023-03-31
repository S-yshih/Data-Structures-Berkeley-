package deque;

import org.junit.Test;
import java.util.Comparator;
import static org.junit.Assert.*;

public class MaxArrayDequeTest{

    @Test
    public void biggestDawg(){

        class Dawg {
            private int size;
            private int age;
            public Dawg(int s, int a) {
                size = s;
                age = a;
            }
        }

        class BiggestDawg implements Comparator<Dawg> {
            public int compare(Dawg a, Dawg b) {
                return Integer.compare(a.size, b.size);
            }
        }

        class OldestDawg implements Comparator<Dawg> {
            public int compare(Dawg a, Dawg b) {
                return Integer.compare(a.age, b.age);
            }
        }

        BiggestDawg c = new BiggestDawg();
        OldestDawg d = new OldestDawg();

        MaxArrayDeque<Dawg> list = new MaxArrayDeque<Dawg>(c);
        list.addFirst(new Dawg(1,2));
        list.addFirst(new Dawg(2, 4));
        list.addFirst(new Dawg(3, 2));

        assertEquals(3, list.max().size);
        assertEquals(4, list.max(d).age);
    }

}