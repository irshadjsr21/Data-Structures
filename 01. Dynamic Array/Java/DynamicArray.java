/**
 * DynamicArray is a class to represent dynamic array data structure 
 * with various functionalities.
*/
class DynamicArray<T> {
  // To store default capacity of the dynamic array.
  private int DEFAULT_CAPACITY = 10; 
  
  // Stores the current size of the array,i.e., the current number of elements.
  private int size;

  // Stores the current capacity of the array,i.e., how many elemets can the array store.
  private int capacity;

  // This is the actual array.
  private T[] arr;

  /** 
   * Public constructor to initialize DynamicArray with default capacity. 
   */
  public DynamicArray() {
    this.size = 0;
    this.capacity = this.DEFAULT_CAPACITY;
    this.arr = (T[]) new Object[capacity];
  }

  /** 
   * Public constructor to initialize DynamicArray with given capacity. 
   */
  public DynamicArray(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    this.arr = (T[]) new Object[capacity];
  }

  /** 
   * Adds element at the end of the array.
   *
   * Complexity: 
   *   Average Case (Array don't need resizing) - O(1)
   *   Worst Case (Array needs resizing) - O(n)
   */
  public void push(T elem) {
    if(size > capacity-1) {
      this.capacity *= 2;
      T[] newArr = (T[]) new Object[this.capacity];
      for(int i=0;i<size;i++) {
        newArr[i] = this.arr[i];
      }
      this.arr = newArr;
    }
    arr[size++] = elem;
  }

  /**
   * Removes the element at the end of the array and returns it. 
   * Returns `null` if array is empty.
   *
   * Complexity: O(1)
   */
  public T pop() {
    if(size <= 0) {
      return null;
    }
    return arr[--size];
  }

  /**
   * Removes the given element from the array.  
   * Returns `true` if element is removed otherwise returns `false`.
   *
   * Complexity: O(n)
   */
  public boolean remove(T elem) {
    int index = this.indexOf(elem);
    if(index == -1) {
      return false;
    }

    size--;
    for(int i=index; i<size;i++) {
      arr[i] = arr[i+1];
    }
    return true;
  }

  /**
   * Replace the element at given index by the given value in the array.  
   * Returns `true` if element is updated otherwise returns `false`.
   *
   * Complexity: O(1)
   */
  public boolean update(int index, T elem) {
    if(index >= size) {
      return false;
    }

    this.arr[index] = elem;
    return true;
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
    for(int i=0;i<size;i++) {
      if(elem.equals(arr[i])) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the size of the array.
   *
   * Complexity: O(1)
   */
  public int size() {
    return size;
  }

  /**
   * Displays the array elements.
   *
   * Complexity: O(n)
   */
  public void display() {
    for(int i=0;i<size;i++) {
      System.out.println(arr[i].toString());
    }
  }
}
