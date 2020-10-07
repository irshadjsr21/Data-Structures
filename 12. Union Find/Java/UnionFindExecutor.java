import java.io.*;
import java.util.*;

/**
 * This is the main class which executes the UnionFind class from the given
 * inputs in `input.txt` file.
 */
class UnionFindExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    String capStr = br.readLine();

    int cap = Integer.parseInt(capStr);
    UnionFind<Integer> arr = new UnionFind<Integer>(cap);
    String line = null;
    while ((line = br.readLine()) != null) {
      String lineArr[] = line.split(" ");
      if (lineArr.length > 0) {
        int c = Integer.parseInt(lineArr[0]);
        Integer elem, elem2, key;
        switch (c) {
          // Add element.
          case 1:
            elem = Integer.parseInt(lineArr[1]);
            arr.push(elem);
            break;
          // Finds the group of the given element.
          case 2:
            elem = Integer.parseInt(lineArr[1]);
            System.out.println(arr.find(elem));
            break;
          // Union of given two elements.
          case 3:
            elem = Integer.parseInt(lineArr[1]);
            elem2 = Integer.parseInt(lineArr[2]);
            arr.union(elem, elem2);
            break;
          // Displays the stcture of the UnionFind.
          case 4:
            arr.display();
            break;
        }
      }
    }

    br.close();
  }
}
