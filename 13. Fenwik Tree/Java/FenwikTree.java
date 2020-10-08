/**
 * FenwikTree is a class to represent Union Find data structure with various
 * functionalities.
 */
class FenwikTree {
  // To store default capacity of the FenwikTree.
  private int DEFAULT_CAPACITY = 16;

  // Stores the current size of the FenwikTree.
  private int size;

  // Stores the current capacity of the FenwikTree,i.e., how many elemets can the
  // FenwikTree store.
  private int capacity;

  // Stores the actual FenwikTree
  private Double[] arr;

  /**
   * Public constructor to initialize FenwikTree with default capacity.
   */
  public FenwikTree() {
    this.size = 0;
    this.capacity = this.DEFAULT_CAPACITY;
    this.arr = new Double[capacity];
    for (int i = 0; i < capacity; i++) {
      this.arr[i] = Double.valueOf(0);
    }
  }

  /**
   * Public constructor to initialize FenwikTree with given capacity.
   */
  public FenwikTree(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    this.arr = new Double[this.capacity];
    for (int i = 0; i < capacity; i++) {
      this.arr[i] = Double.valueOf(0);
    }
  }

  /**
   * Internal method to return the least significant bit of the given number.
   *
   * Here, least significant bit is the position of first `1` in the binary
   * representation of the number.
   */
  private int getLSB(int index) {
    int i = 0;
    while (index % 2 != 1) {
      index = index >> 1;
      i++;
    }
    return i;
  }

  /**
   * Internal method to return the next index of the element which depends on the
   * given index.
   */
  private int getOneUp(int index) {
    int lsb = this.getLSB(index);
    index = index + (int) Math.pow(2, lsb);
    return index;
  }

  /**
   * Adds element to the FenwikTree.
   *
   * Complexity: O(log(n))
   */
  public void push(Double elem) {
    if (this.size >= this.capacity - 1) {
      throw new OutOfMemoryError();
    }

    this.arr[size++] += elem;
    int nextIndex = this.getOneUp(size);
    while (nextIndex < capacity) {
      this.arr[nextIndex - 1] += elem;
      nextIndex = this.getOneUp(nextIndex);
    }
  }

  /**
   * Public method to find the sum till the given index.
   *
   * Complexity: O(log(n))
   */
  public Double sum(int index) {
    if (index < 0)
      throw new IllegalArgumentException("Index should not be less than 0.");

    if (index >= size)
      return null;

    Double sum = this.arr[index];
    int lsb = this.getLSB(index + 1);
    int stepDown = (int) Math.pow(2, lsb);
    int nextIndex = index - stepDown;
    while (nextIndex >= 0) {
      sum += this.arr[nextIndex];

      lsb = this.getLSB(nextIndex + 1);
      stepDown = (int) Math.pow(2, lsb);
      nextIndex = nextIndex - stepDown;
    }

    return sum;
  }

  /**
   * Public method to find the sum in the range of the given two indexes.
   *
   * Complexity: O(log(n))
   */
  public Double range(int index1, int index2) {
    if (index1 > index2)
      throw new IllegalArgumentException("First index should be less than second index.");

    if (index1 < 0 || index2 < 0)
      throw new IllegalArgumentException("Index should not be less than 0.");

    if (index1 >= size || index2 >= size)
      throw new IllegalAccessError("Index should not be greater than size.");

    Double sum1 = index1 <= 0 ? 0 : this.sum(index1 - 1);
    Double sum2 = this.sum(index2);

    return sum2 - sum1;
  }

  /**
   * Public method to find the value of the given index.
   *
   * Complexity: O(log(n))
   */
  public Double get(int index) {
    if (index >= size)
      throw new IllegalAccessError("Index should not be greater than size.");

    return this.range(index, index);
  }

  /**
   * Public method to update the value of the given index.
   *
   * Complexity: O(log(n))
   */
  public boolean update(int index, Double value) {
    if (index >= size || index < 0)
      return false;

    Double prevValue = this.get(index);
    Double diff = value - prevValue;

    this.arr[index] += diff;
    int nextIndex = this.getOneUp(index + 1);
    while (nextIndex < capacity) {
      this.arr[nextIndex - 1] += diff;
      nextIndex = this.getOneUp(nextIndex);
    }

    return true;
  }

  /**
   * Displays the FenwikTree elements.
   *
   * Complexity: O(n)
   */
  public void display() {
    System.out.println();
    for (int i = 0; i < size; i++) {
      System.out.println(i + " : " + this.arr[i]);
    }
    System.out.println();
  }
}
