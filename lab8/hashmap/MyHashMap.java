package hashmap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Iterator;


/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    // Below variables are defined by me
    private int Size;
    private double loadFactor;

    /** Constructors */
    public MyHashMap() {
        //I wrote stuff in this constructor
        this.Size = 16;
        this.loadFactor = 0.75;
        this.buckets = new Collection[Size];
    }

    public MyHashMap(int initialSize) {
        //I wrote stuff in this constructor
        this.Size = initialSize;
        this.loadFactor = 0.75;
        this.buckets = new Collection[Size];
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        //I wrote stuff in this constructor
        this.Size = initialSize;
        this.loadFactor = maxLoad;
        this.buckets = new Collection[Size];
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        // help is this rly it????
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // ??? do i just do a random thing
        // bc the bucket classes override this??
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
        // IDK WHAT A TABLE IS

    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // I added the method names...

    @Override
    public void clear(){

    }
    @Override
    public boolean containsKey(K key){
        return false;
    }
    @Override
    public V get(K key){
        return null;
    }
    @Override
    public int size(){
        return this.Size;
    }
    @Override
    public void put(K key, V value){

    }
    @Override
    public Set<K> keySet(){
        return null;
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
        return null;
    }
}
