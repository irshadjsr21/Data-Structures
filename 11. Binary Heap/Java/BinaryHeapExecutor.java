import java.io.*;
import java.util.*;

/**
 * This is the main class which executes the BinaryHeap class from the given
 * inputs in `input.txt` file.
 */
class BinaryHeapExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    String capStr = br.readLine();

    int cap = Integer.parseInt(capStr);
    BinaryHeap<Integer> arr = new BinaryHeap<Integer>(cap);
    String line = null;
    while ((line = br.readLine()) != null) {
      String lineArr[] = line.split(" ");
      if (lineArr.length > 0) {
        int c = Integer.parseInt(lineArr[0]);
        Integer elem, key;
        switch (c) {
          // Add element in the Heap.
          case 1:
            elem = Integer.parseInt(lineArr[1]);
            arr.add(elem);
            break;
          // Removes the topmost element in the Heap.
          case 2:
            System.out.println(arr.poll());
            break;
          // Returns the topmost element in the Heap.
          case 3:
            System.out.println(arr.peek());
            break;
          // Removes the given element from the Heap.
          case 4:
            elem = Integer.parseInt(lineArr[1]);
            if (arr.remove(elem)) {
              System.out.println("Removed");
            } else {
              System.out.println("Not found");
            }
            break;
          // Displays the Heap.
          case 5:
            arr.display();
            break;
        }
      }
    }

    br.close();
  }
}
