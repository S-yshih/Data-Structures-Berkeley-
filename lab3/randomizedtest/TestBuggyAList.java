package randomizedtest;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> buggy = new BuggyAList<>();

        correct.addLast(1);
        correct.addLast(2);
        correct.addLast(3);

        buggy.addLast(1);
        buggy.addLast(2);
        buggy.addLast(3);

        assertEquals(correct.size(), buggy.size());
        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
        assertEquals(correct.removeLast(), buggy.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
                assertEquals(L.getLast(), B.getLast());
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int sizeb = B.size();
                System.out.println("size: " + size);
                assertEquals(size, sizeb);
            } else if (operationNumber == 2) {
                if (L.size() > 0){
                    int last = L.getLast();
                    int lastb = B.getLast();
                    System.out.println("last: " + last);
                    assertEquals(last, lastb);
                }
            } else if (operationNumber == 3) {
                if (L.size() > 0) {
                    int removed = L.removeLast();
                    int removedb = B.removeLast();
                    System.out.println("removed: " + removed);
                    assertEquals(removed, removedb);
                }
            }
        }
    }
}
