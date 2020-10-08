import java.io.*;
import java.util.*;

/**
 * This is the main class which executes the FenwikTree class from the given
 * inputs in `input.txt` file.
 */
class FenwikTreeExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    String capStr = br.readLine();

    int cap = Integer.parseInt(capStr);
    FenwikTree arr = new FenwikTree(cap);
    String line = null;
    while ((line = br.readLine()) != null) {
      String lineArr[] = line.split(" ");
      if (lineArr.length > 0) {
        int c = Integer.parseInt(lineArr[0]);
        Double elem;
        int index, index2;
        switch (c) {
          // Add element.
          case 1:
            elem = Double.parseDouble(lineArr[1]);
            arr.push(elem);
            break;
          // Update element at the given index.
          case 2:
            index = Integer.parseInt(lineArr[1]);
            elem = Double.parseDouble(lineArr[2]);
            if (arr.update(index, elem))
              System.out.println("Updated");
            else
              System.out.println("Not updated");
            break;
          // Find the sum till the given index.
          case 3:
            index = Integer.parseInt(lineArr[1]);
            System.out.println(arr.sum(index));
            break;
          // Find the sum in the range of the given two indexes.
          case 4:
            index = Integer.parseInt(lineArr[1]);
            index2 = Integer.parseInt(lineArr[2]);
            System.out.println(arr.range(index, index2));
            break;
          // Find the value of the element at the given index.
          case 5:
            index = Integer.parseInt(lineArr[1]);
            System.out.println(arr.get(index));
            break;
          // Displays the structure of the FenwikTree.
          case 6:
            arr.display();
            break;
        }
      }
    }

    br.close();
  }
}
