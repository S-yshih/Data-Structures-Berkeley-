package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertTrue(changed);
    }

    @Test
    public void testNoPrimes(){
        IntList lst = IntList.of(4, 10, 20, 40);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 10 -> 20 -> 40", lst.toString());
        assertFalse(changed);
    }

    @Test
    public void testAllPrimes(){
        IntList lst = IntList.of(2, 5, 7, 5);
        boolean changed = IntListExercises.squarePrimes(lst);
        assertEquals("4 -> 25 -> 49 -> 25", lst.toString());
        assertTrue(changed);
    }

}
