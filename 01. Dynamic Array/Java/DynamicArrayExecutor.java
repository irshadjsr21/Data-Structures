import java.io.*;

/**
 * This is the main class which executes the DynamicArray class
 * from the given inputs in `input.txt` file.
 */
class DynamicArrayExecutor {
  public static void main(String args[]) throws IOException {
    FileReader file = new FileReader("input.txt");
    BufferedReader br = new BufferedReader(file);

    String capStr = br.readLine();

    int cap = Integer.parseInt(capStr);
    DynamicArray<Integer> arr = new DynamicArray<Integer>(cap);
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
          // Remove the given element
          case 3:
            elem = Integer.parseInt(lineArr[1]);
            arr.remove(elem);
            break;
          // update elem at the given index
          case 4:
            index = Integer.parseInt(lineArr[1]);
            elem = Integer.parseInt(lineArr[2]);
            arr.update(index, elem);
            break;
          // Display array elements
          case 5:
            arr.display();
            break;
        }
      }
    }

    br.close();
  }
}
