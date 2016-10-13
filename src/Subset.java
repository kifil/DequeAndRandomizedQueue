import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by User on 10/12/2016.
 */
public class Subset {

    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]);

        RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();

        String s = StdIn.readString();
        while(s != null && s != ""){
            randomQueue.enqueue(s);
            try{
                s = StdIn.readString();
            }
            //since I'm not sure how to emulate command line inputs, break out of the loop when no more strings to read
            catch (Exception e){
                break;
            }
        }

        for(int i = 0; i < k; i++){
            StdOut.println(randomQueue.dequeue());
        }
    }
}
