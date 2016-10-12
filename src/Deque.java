import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;

/**
 * Created by User on 10/11/2016.
 */

public class Deque<ItemType> implements Iterable<ItemType> {
    private class Node{
        ItemType item;
        Node nextNode;
        Node previousNode;
    }
    private int dequeSize;
    private Node firstNode;
    private Node lastNode;

    // construct an empty deque
    public Deque() {
        dequeSize = 0;
    }

    // is the deque empty?
    public boolean isEmpty(){
        return dequeSize == 0;
    }

    // return the number of items on the deque
    public int size() {
        return dequeSize;
    }

    public void addFirst(ItemType item) {
        Node newFirstNode = new Node();

        newFirstNode.item = item;

        if(dequeSize == 0){
            //if list is empty, set this as the first and last node
            firstNode = newFirstNode;
            lastNode = newFirstNode;
        }
        else{
            //otherwise make the old first node point to this node
            newFirstNode.nextNode = firstNode;
            firstNode.previousNode = newFirstNode;
            //reset first node pointer
            firstNode = newFirstNode;
        }

        dequeSize++;
    }

    // add the item to the front
    public void addLast(ItemType item) {
        Node newLastNode = new Node();

        newLastNode.item = item;

        if(dequeSize == 0){
            //if list is empty, set this as the first and last node
            firstNode = newLastNode;
            lastNode = newLastNode;
        }
        else{
            //otherwise make the old last node point to this node
            newLastNode.previousNode = lastNode;
            lastNode.nextNode= newLastNode;
            //reset last node pointer
            lastNode = newLastNode;
        }
        dequeSize++;

    }
    // add the item to the end


    public ItemType removeFirst() {
        if(dequeSize == 0){
            throw new java.util.NoSuchElementException();
        }

        Node oldFirstNode = firstNode;
        firstNode = firstNode.nextNode;

        //if this isnt the last element in the array
        if(firstNode != null){
            firstNode.previousNode = null;
        }

        dequeSize--;

        return oldFirstNode.item;
    }

    // remove and return the item from the front
    public ItemType removeLast(){
        if(dequeSize == 0){
            throw new java.util.NoSuchElementException();
        }

        Node oldLastNode = lastNode;
        lastNode= lastNode.previousNode;

        //if this isnt the last element in the array
        if(lastNode!= null){
            lastNode.nextNode = null;
        }
        dequeSize--;

        return oldLastNode.item;
    }                 // remove and return the item from the end

    public Iterator<ItemType> iterator() {return new ListIterator();}

    private class ListIterator implements Iterator<ItemType> {
        private Node currentNode = firstNode;

        public boolean hasNext() {
            return currentNode != null;
        }

        public ItemType next() {
            if (currentNode == null) {
                throw new java.util.NoSuchElementException();
            }

            Node oldCurrentNode = currentNode;
            currentNode = currentNode.nextNode;

            return oldCurrentNode.item;
        }

        public void Remove() {
            throw new java.lang.UnsupportedOperationException();
        }
    }

    // test client (described below)
    public static void main(String[] args) {
        Deque<Integer> myDeque = new Deque<Integer>();

        myDeque.addFirst(1);
        myDeque.addFirst(2);
        myDeque.addFirst(3);
        myDeque.addFirst(4);
        myDeque.addFirst(5);

        myDeque.addLast(0);


        ///543210
        for (Integer num: myDeque) {
            StdOut.println( num );
        }
        System.out.println("hi");
        ///543210
        StdOut.println( myDeque.removeFirst() );
        StdOut.println( myDeque.removeFirst() );
        StdOut.println( myDeque.removeFirst() );
        StdOut.println( myDeque.removeFirst() );
        StdOut.println( myDeque.removeFirst() );
        StdOut.println( myDeque.removeFirst() );


        //2 1 999
        myDeque.addFirst(1);
        myDeque.addFirst(2);
        myDeque.addLast(999);

        for (Integer num: myDeque) {
            StdOut.println( num );
        }

        myDeque.removeLast();

        //2 1
        for (Integer num: myDeque) {
            StdOut.println( num );
        }

        //false
        StdOut.println( myDeque.isEmpty() );

        //2
        StdOut.println( myDeque.size() );

        myDeque.removeLast();
        myDeque.removeLast();
        //true
        StdOut.println( myDeque.isEmpty() );

    }
}

