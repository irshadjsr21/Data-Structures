import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * HashTable is a class to represent a hash table data structure with various
 * functionalities.
 *
 * This uses Open Addressing and Quadratic Probing.
 *
 * Probing function: ((x^2) + x) / 2
 *
 * It also requires the size of the table to be a power of 2 and the fill
 * capacity to be 0.4
 */
class HashTable<T> {
  // This stores a unique object to represent a Toumb Stone.
  private HashNode TOUMB_STONE = new HashNode();

  // To store default capacity of the table.
  private int DEFAULT_CAPACITY = 16;

  // To store the maximum capacity of the table.
  private double FILL_CAPACITY = 0.4;

  // Stores the current capacity of the table.
  private int capacity;

  // Stores the current size of the table.
  private int size;

  /**
   * HashNode represents a single node of the HashTable.
   */
  class HashNode {
    // This stores the value.
    public T value;

    // This stores the key for the value.
    public int key;

    public HashNode() {
    }

    /**
     * Public constructor of HashNode class.
     */
    public HashNode(int key, T value) {
      this.key = key;
      this.value = value;
    }
  }

  // This represents the table.
  private HashNode[] arr;

  // This stores all the keys.
  private ArrayList<Integer> keys;

  /**
   * Public constructor to initialize HashTable with default capacity.
   */
  public HashTable() {
    this.capacity = this.DEFAULT_CAPACITY;
    this.size = 0;
    this.arr = (HashNode[]) Array.newInstance(HashNode.class, capacity);
    this.keys = new ArrayList<Integer>();
  }

  /**
   * Public constructor to initialize HashTable with given capacity.
   */
  public HashTable(int capacity) {
    this.capacity = capacity;
    this.size = 0;
    this.arr = (HashNode[]) Array.newInstance(HashNode.class, this.getNextPowerOfTwo(capacity));
    this.keys = new ArrayList<Integer>();
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
   * Returns the hash for the given key.
   */
  private int getHash(int key) {
    return key % capacity;
  }

  /**
   * Returns the probing value for the given iteration count.
   */
  private int getProbe(int key) {
    return ((key * key) + key) / 2;
  }

  /**
   * Add or update the key in the HashTable.
   *
   * Complexity: Average: O(1) Worst: O(n)
   */
  public void setKey(int key, T value) {
    if (this.size >= this.FILL_CAPACITY * capacity) {
      this.resizeTable();
    }

    int hash = getHash(key);
    int index = hash;
    int x = 1;
    while (this.arr[index] != null) {
      if (this.arr[index].key == key) {
        break;
      }

      index = (hash + this.getProbe(x++)) % capacity;
    }

    if (this.arr[index] == null) {
      HashNode newNode = new HashNode(key, value);
      this.arr[index] = newNode;
      keys.add(key);
      this.size++;
    } else {
      this.arr[index].value = value;
    }
  }

  /**
   * Return the value at the given key.
   *
   * Complexity: Average: O(1) Worst: O(n)
   */
  public T getKey(int key) {
    int hash = getHash(key);
    int index = hash;
    int x = 1;

    while (this.arr[index] != null) {
      if (this.arr[index] == TOUMB_STONE) {
        index = (hash + this.getProbe(x++)) % capacity;
        continue;
      }

      if (this.arr[index].key == key) {
        break;
      }

      index = (hash + this.getProbe(x++)) % capacity;
    }

    HashNode foundNode = this.arr[index];
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
    int keyIndex = this.keys.indexOf(key);

    if (keyIndex == -1 || this.arr[hash] == null) {
      return false;
    }

    int index = hash;
    int x = 1;
    while (this.arr[index] != null) {
      if (this.arr[index].key == key) {
        break;
      }

      index = (hash + this.getProbe(x++)) % capacity;
    }

    if (this.arr[index] != null && this.arr[index] != TOUMB_STONE) {
      HashNode node = this.arr[index];
      this.keys.remove(keyIndex);
      node.value = null;
      node.key = 0;
      node = null;
      this.arr[index] = TOUMB_STONE;
    }
    return false;
  }

  /**
   * Displays the structure of the HashTable.
   *
   * Complexity: O(n)
   */
  public void displayStructure() {
    int total = 0;
    for (int i = 0; i < this.arr.length; i++) {
      if (this.arr[i] == null)
        continue;

      if (this.arr[i] == TOUMB_STONE) {
        System.out.println("Index: " + i + " contains: TOUMB_STONE");
        total++;
        continue;
      }
      System.out.println("Index: " + i + " contains: " + this.arr[i].key + " - " + this.arr[i].value);
      total++;
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
    int newSize = 0;
    HashNode[] newTable = (HashNode[]) Array.newInstance(HashNode.class, capacity);
    for (int i = 0; i < this.arr.length; i++) {
      HashNode oldBucket = this.arr[i];

      if (oldBucket != null && oldBucket != TOUMB_STONE) {
        int hash = getHash(oldBucket.key);
        int index = hash;
        int x = 1;
        while (newTable[index] != null) {
          index = (hash + this.getProbe(x++)) % capacity;
        }

        newTable[index] = oldBucket;
        newSize++;
      }

      this.arr[i] = null;
    }

    this.arr = newTable;
    this.size = newSize;
  }
}
