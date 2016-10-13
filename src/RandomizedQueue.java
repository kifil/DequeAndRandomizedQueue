import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

/**
 * Created by User on 10/12/2016.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] itemArray;
    private int firstOpenIndex; //rename to firstOpenIndex

    // construct an empty randomized queue
    public RandomizedQueue() {
        //typecasting needed for generic arrays
        itemArray = (Item[]) new Object[1];
        firstOpenIndex = 0;
    }

    // is the queue empty?
    public boolean isEmpty()  {
        return firstOpenIndex == 0;
    }

    // return the number of items on the queue
    public int size(){
        return firstOpenIndex;
    }

    // add the item
    public void enqueue(Item item) {
        if(item == null){
            throw new java.lang.NullPointerException();
        }

        //double array if we are at capacity and copy elements
        if(firstOpenIndex == itemArray.length){
            resize(itemArray.length * 2);
        }

        itemArray[firstOpenIndex] = item;
        firstOpenIndex++;
    }

    //resize the array and copy the elements to the new array
    private void resize(int newSize){
        Item[] newItemArray = (Item[]) new Object[newSize];
        for(int i = 0; i < firstOpenIndex; i++){
            newItemArray[i] = itemArray[i];
        }
        //set the array to the resized copy
        itemArray = newItemArray;
    }

    // remove and return a random item
    public Item dequeue(){
        if(isEmpty()){
            throw new java.util.NoSuchElementException();
        }

        int randomIndex = StdRandom.uniform(0,firstOpenIndex);

        Item randomItem = itemArray[randomIndex];

        //move end item to replace removed item to keep a contiguous array
        itemArray[randomIndex] = itemArray[firstOpenIndex - 1];
        itemArray[firstOpenIndex - 1] = null;

        firstOpenIndex--;

        //shrink array in half if we are 1/4 full
        if(firstOpenIndex - 1 <= itemArray.length / 4 && itemArray.length >= 4){
            resize(itemArray.length / 2);
        }

        return randomItem;
    }

    // return (but do not remove) a random item
    public Item sample(){
        if(isEmpty()){
            throw new java.util.NoSuchElementException();
        }

        int randomIndex = StdRandom.uniform(0,firstOpenIndex);

        return itemArray[randomIndex];
    }

    public Iterator<Item> iterator() {return new ListIterator();}

    private class ListIterator implements Iterator<Item> {
        int currentIndex = 0;
        Item[] iteratorArray = (Item[]) new Object[firstOpenIndex];

        private ListIterator() {
            for (int i = 0; i < iteratorArray.length; i++) {
                iteratorArray[i] = itemArray[i];
            }

            //randomize the array so each iterator returns different items
            StdRandom.shuffle(iteratorArray);
        }

        public boolean hasNext() {
            return currentIndex < iteratorArray.length;
        }

        public Item next() {
            if (currentIndex == iteratorArray.length) {
                throw new java.util.NoSuchElementException();
            }

            Item itemToReturn = iteratorArray[currentIndex];
            currentIndex++;

            return itemToReturn;
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> myRandomQueue = new RandomizedQueue<Integer>();

        myRandomQueue.enqueue(1);
        myRandomQueue.enqueue(2);
        myRandomQueue.enqueue(3);
        myRandomQueue.enqueue(4);
        myRandomQueue.enqueue(5);

        StdOut.println(" random 1- 5");
        for(Integer number : myRandomQueue){
            StdOut.println(number);
        }

        StdOut.println(" 2 random 1-5");
        StdOut.println(myRandomQueue.sample());
        StdOut.println(myRandomQueue.sample());

        StdOut.println("dequeue");
        StdOut.println(myRandomQueue.dequeue());
        StdOut.println(myRandomQueue.dequeue());
        StdOut.println(myRandomQueue.dequeue());

        StdOut.println("size == 2");
        StdOut.println(myRandomQueue.size());

        StdOut.println("isEmpty == false");
        StdOut.println(myRandomQueue.isEmpty());


        myRandomQueue.enqueue(9);
        myRandomQueue.enqueue(10);

        StdOut.println("size == 4");
        StdOut.println(myRandomQueue.size());

        StdOut.println("dequeue all");
        StdOut.println(myRandomQueue.dequeue());
        StdOut.println(myRandomQueue.dequeue());
        StdOut.println(myRandomQueue.dequeue());
        StdOut.println(myRandomQueue.dequeue());

        StdOut.println("isEmpty == true");
        StdOut.println(myRandomQueue.isEmpty());

        myRandomQueue.enqueue(1);
        myRandomQueue.enqueue(2);
        myRandomQueue.enqueue(3);

        StdOut.println("independeent iterators");
        for(Integer number : myRandomQueue){
            StdOut.println(number);
            for(Integer numberNested : myRandomQueue){
                StdOut.println(numberNested);
            }
        }
    }
}

