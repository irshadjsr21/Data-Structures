/**
 * ArrayStack is a class to represent an array stack data structure 
 * with various functionalities.
*/
class ArrayStack<T> {
  // To store default capacity of the dynamic array.
  private int DEFAULT_CAPACITY = 10; 
  
  // Stores the index of top most element in the array.
  private int top;

  // Stores the current capacity of the array,i.e., how many elemets can the array store.
  private int capacity;

  // This is the actual array.
  private T[] arr;

  /** 
   * Public constructor to initialize ArrayStack with default capacity. 
   */
  public ArrayStack() {
    this.top = 0;
    this.capacity = this.DEFAULT_CAPACITY;
    this.arr = (T[]) new Object[capacity];
  }

  /** 
   * Public constructor to initialize ArrayStack with given capacity. 
   */
  public ArrayStack(int capacity) {
    this.top = 0;
    this.capacity = capacity;
    this.arr = (T[]) new Object[capacity];
  }

  /** 
   * Adds element to the stack.
   *
   * Complexity: 
   *   Average Case (Array don't need resizing) - O(1)
   *   Worst Case (Array needs resizing) - O(n)
   */
  public void push(T elem) {
    if(top > capacity-1) {
      this.capacity *= 2;
      T[] newArr = (T[]) new Object[this.capacity];
      for(int i=0;i<top;i++) {
        newArr[i] = this.arr[i];
      }
      this.arr = newArr;
    }
    arr[top++] = elem;
  }

  /**
   * Removes the element at the end of the array and returns it. 
   * Returns `null` if array is empty.
   *
   * Complexity: O(1)
   */
  public T pop() {
    if(top <= 0) {
      return null;
    }
    return arr[--top];
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
    for(int i=0;i<top;i++) {
      if(elem.equals(arr[i])) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the size of the stack.
   *
   * Complexity: O(1)
   */
  public int size() {
    return top;
  }

  /**
   * Displays the stack elements.
   *
   * Complexity: O(n)
   */
  public void display() {
    for(int i=top - 1;i>=0;i--) {
      System.out.println(arr[i].toString());
    }
    System.out.println();
  }
}
