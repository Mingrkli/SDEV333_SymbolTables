import java.io.*;
import java.util.*;

/**
 * Refer to p. 372 of the Algorithms book
 */
public class FrequencyCounter {
    // private SymbolTable<String, Integer> table;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("leipzig100K.txt"));

        // create and empty symbol table (map)
        SymbolTable<String, Integer> table = new BST<>();

        while (in.hasNext()) {
            String word = in.next();

            // check if word is in the table
            if (!table.contains(word)) {
                table.put(word, 1);
            }
            else {
                // word is in the table - update the value
                int count = table.get(word);
                count++;
                table.put(word, count);
                // table.put(word, table.get(word) + 1); the short way
            }
        } // end while

        // print out the table
        for (String word : table.keys()) {
            System.out.println(word + " : " + table.get(word));
        }
    }
}
