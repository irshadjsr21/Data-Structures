import java.io.*;

/**
 * This is the main class which executes the LinkedList class
 * from the given inputs in `input.txt` file.
 */
class LinkedListExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    LinkedList<Integer> list = new LinkedList<Integer>();
    String line = null;
    while((line = br.readLine()) != null) {
      String lineArr[] = line.split(" ");
      if(lineArr.length > 0) {
        int c = Integer.parseInt(lineArr[0]);
        int elem, index;
        switch(c) {
          // Add element at the end
          case 1:
            elem = Integer.parseInt(lineArr[1]);
            list.addEnd(elem);
            break;
          // Add element at the start
          case 2:
            elem = Integer.parseInt(lineArr[1]);
            list.addStart(elem);
            break;
          // Remove element from the end
          case 3:
            list.removeEnd();
            break;
          // Remove element from the start
          case 4:
            list.removeStart();
            break;
          // Display the linkedList
          case 5:
            elem = Integer.parseInt(lineArr[1]);
            list.remove(elem);
            break;
          // Display the queue
          case 6:
            list.display();
            break;
        }
      }
    }

    br.close();
  }
}
