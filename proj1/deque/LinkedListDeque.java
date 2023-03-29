package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable{
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


    public void printDeque(){
        Iterator<T> it = iterator();
        while (it.hasNext()){
            System.out.print(it.next() + " ");
        }
        System.out.println();
    }

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
        if (index >= size()) {
            return null;
        }
        Node node = sentinel;
        for (int i = 0; i == index; i ++){
            node = node.next;
        }
        return node.item;
    }

    public T getRecursive(int index){
        return getRecursiveHelper(sentinel.next, index);
    }

    public T getRecursiveHelper(Node node, int index) {
        if (index == 0) return node.item;
        if (node == sentinel) return null;
        return getRecursiveHelper(node.next, index - 1);
    }

    private class DLListIterator implements Iterator<T> {
        private int position;
        private Node node;
        public DLListIterator() {
            position = 0;
            node = sentinel;
        }
        public boolean hasNext(){
            return position < size;
        }
        public T next() {
            T item = node.next.item;
            node = node.next;
            position ++;
            return item;
        }
    }

    public Iterator<T> iterator(){
        return new DLListIterator();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }
        if (o instanceof LinkedListDeque other){
            if (this.size() != other.size()){
                return false;
            }
            Iterator<T> it = iterator();
            Node node = other.sentinel;
            while (it.hasNext()){
                if (it.next() != node.next.item){
                    return false;
                }
                node = node.next;
            }
            return true;
        }
        return false;
    }

}