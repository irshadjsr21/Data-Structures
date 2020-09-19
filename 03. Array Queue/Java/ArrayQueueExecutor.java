import java.io.*;

/**
 * This is the main class which executes the DynamicArray class
 * from the given inputs in `input.txt` file.
 */
class ArrayQueueExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    String capStr = br.readLine();

    int cap = Integer.parseInt(capStr);
    ArrayQueue<Integer> arr = new ArrayQueue<Integer>(cap);
    String line = null;
    while((line = br.readLine()) != null) {
      String lineArr[] = line.split(" ");
      if(lineArr.length > 0) {
        int c = Integer.parseInt(lineArr[0]);
        int elem, index;
        switch(c) {
          case 1:
            elem = Integer.parseInt(lineArr[1]);
            arr.queue(elem);
            break;
          // Pop Last Element
          case 2: 
            arr.dequeue();
            break;
          case 3:
            arr.display();
            break;
        }
      }
    }

    br.close();
  }
}
