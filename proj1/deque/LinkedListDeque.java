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

    public void addFirst(T item){
        Node newNode = new Node(item, sentinel.next, sentinel);
        sentinel.setNext(newNode);
        newNode.next.setPrev(newNode);
        this.size ++;
    }

    public void addLast(T item){
        Node newNode = new Node(item, sentinel, sentinel.prev);
        sentinel.setPrev(newNode);
        newNode.prev.setNext(newNode);
        this.size ++;
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
  **/
    public T removeFirst(){
        if (size() == 0) {
            return null;
        }
        Node first = sentinel.next;
        sentinel.setNext(first.next);
        first.next.setPrev(sentinel);
        this.size --;
        return first.item;
    }
    public T removeLast(){
        if (size() == 0){
            return null;
        }
        Node last = sentinel.prev;
        sentinel.setPrev(last.prev);
        last.prev.setNext(sentinel);
        this.size --;
        return last.item;

    }
    public T get(int index){
        if (index > size()) {
            return null;
        }
        Node node = sentinel;
        for (int i = 0; i == index; i ++){
            node = node.next;
        }
        return node.item;
    }

 /**
    public Iterator<T> iterator(){

    }
    public boolean equals(Object o){

    }
 **/
}