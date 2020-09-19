import java.io.*;

/**
 * This is the main class which executes the ArrayStack class
 * from the given inputs in `input.txt` file.
 */
class ArrayStackExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    String capStr = br.readLine();

    int cap = Integer.parseInt(capStr);
    ArrayStack<Integer> arr = new ArrayStack<Integer>(cap);
    String line = null;
    while((line = br.readLine()) != null) {
      String lineArr[] = line.split(" ");
      if(lineArr.length > 0) {
        int c = Integer.parseInt(lineArr[0]);
        int elem, index;
        switch(c) {
          // Push Element
          case 1:
            elem = Integer.parseInt(lineArr[1]);
            arr.push(elem);
            break;
          // Pop Last Element
          case 2: 
            arr.pop();
            break;
          // Display array elements
          case 3:
            arr.display();
            break;
        }
      }
    }

    br.close();
  }
}
