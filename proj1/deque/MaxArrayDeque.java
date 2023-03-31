package deque;


import java.util.Comparator;
import java.util.Iterator;

public class MaxArrayDeque<T> extends ArrayDeque{
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c){
        //creates max array deque with given comparator
        super();
        this.comparator = c;
    }

    public T max(){
        //returns max elem in the deque (return null if empty)
        return max(this.comparator);
    }

    public T max(Comparator<T> c){
        //returns max elem governed by passed comparator (return null if empty)
        T max = null;
        T prev = null;
        Iterator<T> it = iterator();
        while (it.hasNext()){
            T maxOfTwoItems;
            if (c.compare(prev, it.next()) > 0){
                maxOfTwoItems = prev;
            } else { maxOfTwoItems = it.next();}

            if (c.compare(maxOfTwoItems, max) > 0){
                max = maxOfTwoItems;
            }
            prev = it.next();
        }
        return max;
    }
    @Override
    public boolean equals(Object o){
        return false;
    }

}