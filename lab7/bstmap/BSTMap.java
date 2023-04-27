package bstmap;

import afu.org.checkerframework.checker.oigj.qual.O;

import java.util.Iterator;
import java.util.Set;


public class BSTMap<K extends Comparable, V> implements Map61B<K,V>{
    private class BSTNode {
        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        private BSTNode(K key, V val) {
            this.key = key;
            this.value = val;
            this.left = null;
            this.right = null;
        }
    }


    private int size;
    private BSTNode starter;
    private BSTNode nodeHolder;

    public BSTMap(){
        starter = new BSTNode(null, null);
        nodeHolder = new BSTNode(null, null);
        this.size = 0;
    }


    @Override
    public void clear(){
        starter = new BSTNode(null, null);
        this.size = 0;
    }

    private BSTNode find(BSTNode node, K key) {
        if (node.key == null) {
            return null;
        }
        if ((node.key).equals(key)) {
            return node;
        } else if (key.compareTo(node.key) < 0) {
            return find(node.left, key);
        } else {
            return find(node.right, key);
        }
    }

    @Override
    public boolean containsKey(K key){
        if (find(starter, key) == null){
            return false;
        }
        return true;
    }
    @Override
    public V get(K key){
        BSTNode i = find(starter, key);
        if (i == null){
            return null;
        }
        return i.value;
    }
    @Override
    public int size(){
        return this.size;
    }

    private BSTNode insert(BSTNode node, K key, V val) {
        if (node == null) {
            this.size ++;
            node = new BSTNode(key, val);
            if (key.compareTo(nodeHolder.key) < 0){
                nodeHolder.left = node;
            }
            else {
                nodeHolder.right = node;
            }
            return node;
        }
        if ((node.key).equals(key)) {
            return null;
        } else if (key.compareTo(node.key) < 0) {
            nodeHolder = node;
            return insert(node.left, key, val);
        } else {
            nodeHolder = node;
            return insert(node.right, key, val);
        }
    }
    @Override
    public void put(K key, V value){
        if (starter.key == null) {
            this.starter = new BSTNode(key, value);
            this.size ++;
        } else {
            insert(starter, key, value);
        }
    }
    @Override
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}