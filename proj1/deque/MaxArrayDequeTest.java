package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest{

    @Test
    public void smallestMax(){
        class LeastInteger implements Comparator<Integer> {
            public int compare(Integer a, Integer b) {
                if (a < b) return 1;
                if (a.equals(b)) return 0;
                return -1;
            }
            public Comparator<Integer> leastMaxComparator(){

            }
        }

        MaxArrayDeque<Integer> list = new MaxArrayDeque<>(LeastInteger);

    }
    @Test
    public void

}