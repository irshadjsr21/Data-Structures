import java.util.HashMap;

/**
 * UnionFind is a class to represent Union Find data structure with various
 * functionalities.
 */
class UnionFind<T> {
  // To store default capacity of the array.
  private int DEFAULT_CAPACITY = 16;

  // Stores the current size of the array.
  private int size;

  // Stores the current capacity of the array,i.e., how many elemets can the array
  // store.
  private int capacity;

  // Stores the group value of all the elements.
  private int[] pointArr;

  // Stores the element and it's index.
  private HashMap<T, Integer> valuesMap;

  /**
   * Public constructor to initialize UnionFind with default capacity.
   */
  public UnionFind() {
    this.size = 0;
    this.capacity = this.DEFAULT_CAPACITY;
    this.pointArr = new int[capacity];
    this.valuesMap = new HashMap<T, Integer>(capacity);
  }

  /**
   * Public constructor to initialize UnionFind with given capacity.
   */
  public UnionFind(int capacity) {
    this.size = 0;
    this.capacity = capacity;
    this.pointArr = new int[this.capacity];
    this.valuesMap = new HashMap<T, Integer>(capacity);
  }

  /**
   * Adds element to the array.
   *
   * Complexity:
   *
   * Average Case (Array don't need resizing) - O(1)
   *
   * Worst Case (Array needs resizing) - O(n)
   */
  public void push(T elem) {
    if (size > capacity - 1) {
      this.capacity *= 2;
      int[] newPointsArr = new int[this.capacity];
      for (int i = 0; i < size; i++) {
        newPointsArr[i] = this.pointArr[i];
      }
      this.pointArr = newPointsArr;
    }

    pointArr[size] = size;
    this.valuesMap.put(elem, size++);
  }

  /**
   * Public method to find the group of the given element.
   *
   * Complexity:
   *
   * Average case (When path is optimized): O(1)
   *
   * Worst case (Path needs optimization): O(n)
   */
  public int find(T elem) {
    int index = this.valuesMap.get(elem);

    if (index == -1)
      return -1;

    int parent = index;

    while (this.pointArr[parent] != parent) {
      parent = this.pointArr[parent];
    }

    while (index != parent) {
      int newIndex = this.pointArr[index];
      this.pointArr[index] = parent;
      index = newIndex;
    }

    return parent;
  }

  /**
   * Public method to group two elements.
   *
   * Complexity:
   *
   * Average case (When path is optimized): O(1)
   *
   * Worst case (Path needs optimization): O(n)
   */
  public void union(T elem1, T elem2) {
    int index1 = this.find(elem1);
    int index2 = this.find(elem2);

    if (index1 == -1 || index2 == -1)
      return;

    this.pointArr[index1] = index2;
  }

  /**
   * Displays the array elements.
   *
   * Complexity: O(n)
   */
  public void display() {
    System.out.println();
    for (int i = 0; i < size; i++) {
      System.out.println(i + " : " + pointArr[i]);
    }
    System.out.println();
  }
}
