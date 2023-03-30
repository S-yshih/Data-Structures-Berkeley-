package deque;


import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque{


    public MaxArrayDeque(Comparator<T> c){
        //creates max array deque with given comparator
    }

    public T max(){
        //returns max elem in the deque (return null if empty)
    }

    public T max(Comparator<T> c){
        //returns max elem governed by passed comparator (return null if empty)
    }

}