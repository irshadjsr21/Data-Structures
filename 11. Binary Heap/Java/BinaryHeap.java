/**
 * BinaryHeap is a class to represent Binary Heap data structure with various
 * functionalities.
 */
class BinaryHeap<T extends Comparable> {
  // To store default capacity of the heap.
  private int DEFAULT_CAPACITY = 16;

  // Stores the current size of the heap,i.e., the current number of elements.
  private int size;

  // Stores the current capacity of the heap,i.e., how many elemets can the array
  // store.
  private int capacity;

  // This array stores the actual heap.
  private T[] arr;

  /**
   * Public constructor to initialize BinaryHeap with default capacity.
   */
  public BinaryHeap() {
    this.size = 0;
    this.capacity = this.DEFAULT_CAPACITY;
    this.arr = (T[]) new Comparable[capacity];
  }

  /**
   * Public constructor to initialize BinaryHeap with given capacity.
   */
  public BinaryHeap(int capacity) {
    this.size = 0;
    this.capacity = this.getNextPowerOfTwo(capacity);
    this.arr = (T[]) new Comparable[this.capacity];
  }

  /**
   * Computes and returns the next power of 2 which is greater than `num`.
   */
  private int getNextPowerOfTwo(int num) {
    int value = 1;
    // The following while loop will run until we
    // get a number greater than n
    while (value <= num) {
      // value will be left shifted by 1 place in each iteration
      value = value << 1;
    }
    return value;
  }

  /**
   * Internal method to return the parent index of the node at `index`.
   */
  private int getParent(int index) {
    if (index <= 0)
      return 0;

    return Math.abs((index - 1) / 2);
  }

  /**
   * Internal method to return the left child index of the node at `index`.
   */
  private int getLeftChild(int index) {
    return Math.abs((2 * index) + 1);
  }

  /**
   * Internal method to return the right child index of the node at `index`.
   */
  private int getRightChild(int index) {
    return Math.abs((2 * index) + 2);
  }

  /**
   * Internal method to swap the nodes at the given two indexes.
   */
  private void swap(int a, int b) {
    T temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  /**
   * Internal method to swim the given node up the tree to maintain the heap
   * property.
   */
  private void swim(int currentIndex) {
    while (true) {
      int parent = this.getParent(currentIndex);
      if (currentIndex == parent)
        break;

      if (arr[parent].compareTo(arr[currentIndex]) > 0)
        break;

      this.swap(parent, currentIndex);

      currentIndex = parent;
    }
  }

  /**
   * Internal method to sink the given node down the tree to maintain the heap
   * property.
   */
  private void sink(int currentIndex) {
    while (true) {
      int leftChild = this.getLeftChild(currentIndex);
      int rightChild = this.getRightChild(currentIndex);

      if (leftChild < size && rightChild < size) {
        if (arr[currentIndex].compareTo(arr[leftChild]) > 0 && arr[currentIndex].compareTo(arr[rightChild]) > 0) {
          break;
        }

        if (arr[leftChild].compareTo(arr[rightChild]) > 0) {
          this.swap(leftChild, currentIndex);
          currentIndex = leftChild;
        } else {
          this.swap(rightChild, currentIndex);
          currentIndex = rightChild;
        }
      } else if (leftChild < size && arr[leftChild].compareTo(arr[currentIndex]) > 0) {
        this.swap(leftChild, currentIndex);
        currentIndex = leftChild;
      } else if (rightChild < size && arr[rightChild].compareTo(arr[currentIndex]) > 0) {
        this.swap(rightChild, currentIndex);
        currentIndex = rightChild;
      } else {
        break;
      }
    }
  }

  /**
   * Adds element to the heap.
   *
   * Complexity: O(log(n))
   */
  public void add(T elem) {
    if (size > capacity - 1) {
      this.capacity = this.getNextPowerOfTwo(this.capacity + 1);
      T[] newArr = (T[]) new Comparable[this.capacity];
      for (int i = 0; i < size; i++) {
        newArr[i] = this.arr[i];
      }
      this.arr = newArr;
    }

    arr[size++] = elem;
    int currentIndex = size - 1;

    this.swim(currentIndex);
  }

  /**
   * Returns the topmost element on the heap.
   *
   * Complexity: O(1)
   */
  public T peek() {
    if (size <= 0)
      return null;

    return arr[0];
  }

  /**
   * Removes the topmost element in the heap and returns it. Returns `null` if
   * heap is empty.
   *
   * Complexity: O(log(n))
   */
  public T poll() {
    if (size <= 0)
      return null;

    int currentIndex = 0;
    T elem = arr[currentIndex];
    arr[currentIndex] = arr[size - 1];
    arr[size - 1] = null;
    size--;

    this.sink(currentIndex);
    return elem;
  }

  /**
   * Removes the given elem from the heap. Returns `false` if elem not found.
   *
   * Complexity: O(n)
   */
  public boolean remove(T elem) {
    int currentIndex = -1;
    for (int i = 0; i < size; i++) {
      if (arr[i].equals(elem)) {
        currentIndex = i;
        break;
      }
    }

    if (currentIndex == -1)
      return false;

    arr[currentIndex] = arr[size - 1];
    arr[size - 1] = null;
    size--;

    this.sink(currentIndex);
    this.swim(currentIndex);

    return true;
  }

  /**
   * Returns the size of the heap.
   *
   * Complexity: O(1)
   */
  public int size() {
    return size;
  }

  /**
   * Returns the height of the binary heap.
   *
   * Complexity: O(1)
   */
  public int height() {
    return (int) (Math.log(size) / Math.log(2));
  }

  /**
   * Displays the binary heap.
   *
   * Complexity: O(n)
   */
  public void display() {
    int lastLevel = -1;
    boolean isLeft = true;
    int height = this.height();

    for (int i = 0; i < size; i++) {
      int currentLevel = (int) (Math.log(i + 1) / Math.log(2));
      int spaces = ((int) Math.pow(2, (height - currentLevel))) * 3;
      isLeft = false;

      if (spaces > 0)
        spaces = spaces - 1;

      if (currentLevel > lastLevel) {
        isLeft = true;
        System.out.println();
      }

      for (int j = 0; j < spaces; j++)
        System.out.print(" ");

      System.out.printf("%d", arr[i]);

      spaces++;

      for (int j = 0; j < spaces; j++)
        System.out.print(" ");

      lastLevel = currentLevel;
      isLeft = !isLeft;
    }
    System.out.println();
    System.out.println();
  }
}
