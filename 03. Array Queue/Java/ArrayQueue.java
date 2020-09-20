/**
 * ArrayQueue is a class to represent array queue data structure 
 * with various functionalities.
*/
class ArrayQueue<T> {
  // To store default capacity of the queue.
  private int DEFAULT_CAPACITY = 10; 
  
  // Stores the index of the first element in queue.
  private int start;

  // Stores the index of the last element in queue.
  private int end;

  // Stores the current capacity of the queue,i.e., how many elemets can the queue store.
  private int capacity;

  // This is the actual array.
  private T[] arr;

  /** 
   * Public constructor to initialize ArrayQueue with default size. 
   */
  public ArrayQueue() {
    this.start = -1;
    this.end = -1;
    this.capacity = this.DEFAULT_CAPACITY;
    this.arr = (T[]) new Object[capacity];
  }

  /** 
   * Public constructor to initialize ArrayQueue with given capacity. 
   */
  public ArrayQueue(int capacity) {
    this.start = -1;
    this.end = -1;
    this.capacity = capacity;
    this.arr = (T[]) new Object[capacity];
  }

  /** 
   * Adds element to the queue.
   *
   * Complexity: O(1)
   */
  public void queue(T elem) {
    if(end >= capacity-1) {
      throw new OutOfMemoryError("No free memory.");
    }
    if(start == -1 && end == -1) {
      start = 0;
    }

    arr[++end] = elem;
  }

  /** 
   * Removes first element from the queue and returns it.
   * Returns `null` if array is empty.
   *
   * Complexity: O(1)
   */
  public T dequeue() {
    if(start == -1 || start > end) {
      return null;
    }

    return arr[start++];
  }

  /**
   * Returns `true` if the element exists inside the array, otherwise returns `false`.
   *
   * Complexity: O(n)
   */
  public boolean contains(T elem) {
    return this.indexOf(elem) != -1;
  }
  
  /**
   * Returns the index of the given element if exists, otherwise returns `-1`.
   *
   * Complexity: O(n)
   */
  public int indexOf(T elem) {
    if(start == -1) return -1;

    for(int i=start;i<=end;i++) {
      if(elem.equals(arr[i])) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the size of the array.size
   *
   * Complexity: O(1)
   */
  public int size() {
    if(start == -1) {
      return 0;
    }
    return end - start;
  }

  /**
   * Displays the array elements.
   *
   * Complexity: O(n)
   */
  public void display() {
    if(start != -1) {
      for(int i=start;i<=end;i++) {
        System.out.println(arr[i].toString());
      }
      System.out.println();
    }
  }
}
