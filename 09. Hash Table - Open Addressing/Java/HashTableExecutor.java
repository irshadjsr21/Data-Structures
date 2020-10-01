import java.io.*;
import java.util.*;

/**
 * This is the main class which executes the HashTable class from the given
 * inputs in `input.txt` file.
 */
class HashTableExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    String capStr = br.readLine();

    int cap = Integer.parseInt(capStr);
    HashTable<Integer> arr = new HashTable<Integer>(cap);
    String line = null;
    while ((line = br.readLine()) != null) {
      String lineArr[] = line.split(" ");
      if (lineArr.length > 0) {
        int c = Integer.parseInt(lineArr[0]);
        Integer elem, key;
        switch (c) {
          // Add element at the given key.
          case 1:
            key = Integer.parseInt(lineArr[1]);
            elem = Integer.parseInt(lineArr[2]);
            arr.setKey(key, elem);
            break;
          // Gets the element at the given key.
          case 2:
            key = Integer.parseInt(lineArr[1]);
            elem = arr.getKey(key);
            if (elem == null) {
              System.out.println("Not found");
            } else {
              System.out.println(elem);
            }
            break;
          // Removes the element at the given key.
          case 3:
            key = Integer.parseInt(lineArr[1]);
            arr.removeKey(key);
            break;
          // Displays all the keys in the HashTable
          case 4:
            System.out.println(Arrays.toString(arr.getKeys()));
            break;
          // Displays the structure of the HashTable
          case 5:
            arr.displayStructure();
            break;
        }
      }
    }

    br.close();
  }
}
