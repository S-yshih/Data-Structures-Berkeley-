package deque;

import java.util.Iterator;

public class LinkedListDeque<T>{
    private class Node{
        private T item;
        private Node next;
        private Node prev;

        private Node(T item, Node next, Node prev){
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        private Node(T item){
            this.item = item;
            this.next = null;
            this.prev = null;
        }

        public void setNext(Node next) {
            this.next = next;
        }
        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque(){
        this.size = 0;
        this.sentinel = new Node(null);
        this.sentinel.setNext(sentinel);
        this.sentinel.setPrev(sentinel);
    }





/**
    public void addFirst(T item){

    }
    public void addLast(T item){

    }
    public boolean isEmpty(){
        return (size() == 0);
    }
    public int size(){
        return this.size;
    }
    public void printDeque(){

    }
    public T removeFirst(){

    }
    public T removeLast(){

    }
    public T get(int index){

    }
    public Iterator<T> iterator(){

    }
    public boolean equals(Object o){

    }
 **/
}