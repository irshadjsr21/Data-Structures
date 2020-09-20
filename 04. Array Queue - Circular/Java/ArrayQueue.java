/**
 * ArrayQueue is a class to represent circular array queue data structure 
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
    if((start < end && (end - start) >= capacity -1) || (end < start && (start - end) == 1)) {
      throw new OutOfMemoryError("No free memory.");
    }

    if(start == -1 && end == -1) {
      start = 0;
    }

    end = (end + 1) % capacity;
    arr[end] = elem;
  }

  /** 
   * Removes first element from the queue and returns it.
   * Returns `null` if array is empty.
   *
   * Complexity: O(1)
   */
  public T dequeue() {
    if(start == -1) {
      return null;
    }
    T removedElem = arr[start];

    if(start == end) {
      start = -1;
      end = -1;
    } else {
      start = (start + 1) % capacity;
    }

    return removedElem;
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

    int i=start;
    while(i != end) {
      if(elem.equals(arr[i])) {
        return i;
      }
      i = (i+1) % capacity;
    }
    if(elem.equals(arr[i])) {
      return i;
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
    return Math.abs(end - start);
  }

  /**
   * Displays the array elements.
   *
   * Complexity: O(n)
   */
  public void display() {
    if(start != -1) {
      int i = start;
      while(i != end) {
        System.out.println(arr[i].toString());
        i = (i+1) % capacity;
      }
      System.out.println(arr[i].toString());

      System.out.println();
    }
  }
}
