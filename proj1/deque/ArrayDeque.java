package deque;

import java.util.Iterator;
import java.util.Arrays;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = 0;
        this.nextLast = 1;
    }

    public void addFirst(T item){
        items[nextFirst] = item;
        size ++;
        this.nextFirst = pointerChange(-1, nextFirst);
    }
    public void addLast(T item){
        items[nextLast] = item;
        size ++;
        this.nextLast = pointerChange(1, nextLast);
    }
    /** pass -1 or +1  for add first and add last respectively **/
    public int pointerChange(int change, int pointer) {
        int value = pointer + change;
        if (value < 0) {
            return items.length + value;
        }
        if (value >= items.length) {
            return value - items.length;
        }
        return value;
    }

    public boolean isEmpty(){
        return (size() == 0);
    }
    public int size(){
        return this.size;
    }
/**
    public void printDeque(){

    }
    public T removeFirst(){

    }
    public T removeLast(){

    }
 **/
    public T get(int index){
        if (index >= size){
            return null;
        }
        return null;
    }

/**
    public Iterator<T> iterator(){

    }
    public boolean equals(Object o){

    }
 **/
}