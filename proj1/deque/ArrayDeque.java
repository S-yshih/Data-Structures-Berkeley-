package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable, Deque<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;
    private int FIRST = 6;

    public ArrayDeque(){
        this.items = (T[]) new Object[8];
        this.size = 0;
        this.nextFirst = FIRST;
        this.nextLast = FIRST + 1;
    }

    public void resize(double factor){
        int oldDequeSize = size();
        int newArraySize = (int) (items.length * factor);
        T[] newArray = (T[]) new Object[newArraySize];
        int itemsIndex = pointerChange(1, nextFirst);
        for (int i = 0; i < newArraySize; i ++){
            newArray[i] = items[itemsIndex];
            itemsIndex = pointerChange(1, itemsIndex);
            if (i >= oldDequeSize-1) {
                break;
            }
        }
        this.items = newArray;
        this.nextFirst = pointerChange(-1, 0);
        this.nextLast = pointerChange(1, size()-1);

    }

    public void addFirst(T item){
        if (size() == items.length) {
            resize(2);
        }
        items[nextFirst] = item;
        size ++;
        this.nextFirst = pointerChange(-1, nextFirst);
    }
    public void addLast(T item){
        if (size() == items.length) {
            resize(2);
        }
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

    public int size(){
        return this.size;
    }
    public void printDeque(){
        Iterator<T> it = iterator();
        while (it.hasNext()){
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }


    public T removeFirst(){
        if (this.isEmpty()){
            return null;
        }
        int firstIndex = pointerChange(1, nextFirst);
        T item = items[firstIndex];
        items[firstIndex] = null;
        size --;
        nextFirst = firstIndex;

        if ((items.length > 16) && (size() < items.length * .25)) {
            resize(.50);
        }
        return item;
    }
    public T removeLast(){
        if (this.isEmpty()){
            return null;
        }
        int lastIndex = pointerChange(-1, nextLast);
        T item = items[lastIndex];
        items[lastIndex] = null;
        size --;
        nextLast = lastIndex;
        if ((items.length > 16) && (size() < items.length * .25)) {
            resize(.50);
        }
        return item;
    }

    public T get(int index){
        if (index >= size){
            return null;
        }
        return items[pointerChange(index, pointerChange(1, nextFirst))];
        // the pointerChange within pointerChange is essentially nextFirst + 1
    }

    private class ArrayDequeIterator implements Iterator{
        private int first;
        private int position;

        public ArrayDequeIterator() {
            position = 0;
            first = pointerChange(1, nextFirst);
        }
        public boolean hasNext() {
            return position < size();
        }
        public T next() {
            int nextItemIndex = pointerChange(position, first);
            position ++;
            return items[nextItemIndex];
        }
    }
    public Iterator<T> iterator(){
        return new ArrayDequeIterator();
    }
    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if (o instanceof ArrayDeque other){
            if (this.size() != other.size()) {
                return false;
            }
            Iterator<T> it = iterator();
            int index = other.nextFirst;
            while (it.hasNext()){
                if(other.get(pointerChange(1, index)) == it.next()){
                    return false;
                }
                index = pointerChange(1, index);
            }
            return true;
        }
        return false;
    }

}