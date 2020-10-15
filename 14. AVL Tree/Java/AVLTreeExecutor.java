import java.io.*;
import java.util.*;

/**
 * This is the main class which executes the AVLTree class from the given inputs
 * in `input.txt` file.
 */
class AVLTreeExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    AVLTree<Integer> arr = new AVLTree<Integer>();
    String line = null;
    while ((line = br.readLine()) != null) {
      String lineArr[] = line.split(" ");
      if (lineArr.length > 0) {
        int c = Integer.parseInt(lineArr[0]);
        Integer elem, key;
        switch (c) {
          // Add element in the AVLTree.
          case 1:
            elem = Integer.parseInt(lineArr[1]);
            arr.insert(elem);
            break;
          // Find element in the AVLTree.
          case 2:
            elem = Integer.parseInt(lineArr[1]);
            elem = arr.find(elem);
            if (elem == null) {
              System.out.println("Not found");
            } else {
              System.out.println(elem);
            }
            break;
          // Remove element from the AVLTree.
          case 3:
            elem = Integer.parseInt(lineArr[1]);
            if (!arr.remove(elem)) {
              System.out.println("Not found");
            } else {
              System.out.println("Node removed");
            }
            break;
          // Display the structure of AVLTree.
          case 4:
            arr.displayStructure();
            break;
        }
      }
    }

    br.close();
  }
}
