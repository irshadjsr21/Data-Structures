import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * HashTable is a class to represent a hash table data structure with various
 * functionalities.
 */
class HashTable<T> {
  // To store default capacity of the table.
  private int DEFAULT_CAPACITY = 10;

  // To store the maximum capacity of the table.
  private double FILL_CAPACITY = 0.75;

  // Stores the current capacity of the table,i.e., how many elemets can the array
  // store.
  private int capacity;

  /**
   * HashNode represents a single node of the HashTable.
   */
  class HashNode {
    // This stores the value.
    public T value;

    // This stores the key for the value.
    public int key;

    /**
     * Public constructor of HashNode class.
     */
    public HashNode(int key, T value) {
      this.key = key;
      this.value = value;
    }

    /**
     * Overrides Object equals method. (Use in LinkedList class)
     */
    @Override
    public boolean equals(Object that) {
      if (this == that)
        return true;

      if (that == null)
        return false;

      if (this.getClass() != that.getClass())
        return false;

      HashNode thatNode = (HashNode) that;
      return this.key == thatNode.key;
    }
  }

  // This represents the table.
  private LinkedList<HashNode>[] arr;

  // This stores all the keys.
  private ArrayList<Integer> keys;

  /**
   * Public constructor to initialize HashTable with default capacity.
   */
  public HashTable() {
    this.capacity = this.DEFAULT_CAPACITY;
    this.arr = (LinkedList<HashNode>[]) Array.newInstance(LinkedList.class, capacity);
    this.keys = new ArrayList<Integer>();
  }

  /**
   * Public constructor to initialize HashTable with given capacity.
   */
  public HashTable(int capacity) {
    this.capacity = capacity;
    this.arr = (LinkedList<HashNode>[]) Array.newInstance(LinkedList.class, capacity);
    this.keys = new ArrayList<Integer>();
  }

  /**
   * Returns the hash for the given key.
   */
  private int getHash(int key) {
    return key % capacity;
  }

  /**
   * Add or update the key in the HashTable.
   *
   * Complexity: Average: O(1) Worst: O(n)
   */
  public void setKey(int key, T value) {
    if (this.keys.size() >= this.FILL_CAPACITY * capacity) {
      this.resizeTable();
    }

    int hash = getHash(key);
    HashNode newNode = new HashNode(key, value);
    if (this.arr[hash] == null) {
      LinkedList<HashNode> newList = new LinkedList<HashNode>();
      newList.addEnd(newNode);
      this.arr[hash] = newList;
      keys.add(key);
    } else {
      HashNode updatedKey = this.arr[hash].addOrUpdate(newNode);
      if (updatedKey == null) {
        keys.add(key);
      }
    }
  }

  /**
   * Return the value at the given key.
   *
   * Complexity: Average: O(1) Worst: O(n)
   */
  public T getKey(int key) {
    int hash = getHash(key);
    if (this.arr[hash] == null) {
      return null;
    }

    HashNode foundNode = this.arr[hash].find(new HashNode(key, null));
    if (foundNode == null)
      return null;
    return foundNode.value;
  }

  /**
   * Remove the value at the given key.
   *
   * Complexity: Average: O(1) Worst: O(n)
   */
  public boolean removeKey(int key) {
    int hash = getHash(key);
    int index = this.keys.indexOf(key);
    if (index == -1 || this.arr[hash] == null) {
      return false;
    }

    boolean hasRemoved = this.arr[hash].remove(new HashNode(key, null));
    if (hasRemoved) {
      this.keys.remove(index);
    }

    return hasRemoved;
  }

  /**
   * Displays the structure of the HashTable.
   *
   * Complexity: O(n)
   */
  public void displayStructure() {
    int total = 0;
    for (int i = 0; i < this.arr.length; i++) {
      LinkedList<HashNode> bucket = this.arr[i];
      int len = 0;
      if (bucket != null)
        len = bucket.size();
      System.out.println("Bucket: " + i + " is of size: " + len);
      total += len;
    }
    System.out.println("Total Size: " + total + "\n");
  }

  /**
   * Returns all the keys in the HashTable.
   *
   * Complexity: O(1)
   */
  public Integer[] getKeys() {
    return keys.toArray(new Integer[keys.size()]);
  }

  /**
   * Internal method to resize the HashTable.
   */
  private void resizeTable() {
    System.out.println("Resizing...");
    this.capacity = this.capacity * 2;
    LinkedList<HashNode>[] newTable = (LinkedList<HashNode>[]) Array.newInstance(LinkedList.class, capacity);
    for (int i = 0; i < this.arr.length; i++) {
      LinkedList<HashNode> oldBucket = this.arr[i];

      if (oldBucket != null) {
        Iterator iterator = oldBucket.iterator();
        while (iterator.hasNext()) {
          HashNode oldNode = (HashNode) iterator.next();
          int hash = getHash(oldNode.key);
          if (newTable[hash] == null) {
            LinkedList<HashNode> newList = new LinkedList<HashNode>();
            newList.addEnd(oldNode);
            newTable[hash] = newList;
          } else {
            newTable[hash].addOrUpdate(oldNode);
          }
        }
        oldBucket.clear();
      }

      this.arr[i] = null;
    }

    this.arr = newTable;
  }
}
