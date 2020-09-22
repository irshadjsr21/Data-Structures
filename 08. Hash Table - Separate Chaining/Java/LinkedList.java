import java.lang.Iterable;
import java.util.Iterator;

/**
 * LinkedList is a class to represent Doubly Linked List data structure 
 * with various functionalities.
*/
class LinkedList<T> implements Iterable {

  /**
   * Class to represend a node for the LinkedList
   */
  private static class Node<T> {
    public T value;
    public Node<T> next;
    public Node<T> prev;

    public Node(T value, Node<T> prev, Node<T> next) {
      this.value = value;
      this.next = next;
      this.prev = prev;
    }
  }

  /**
   * ListIterator class to iterate over the LinkedList.
   */
  private static class ListIterator<T> implements Iterator<T> { 
    Node<T> current; 
    Node<T> root; 
    boolean onRoot;
      
    // Initialize pointer to head of the list for iteration 
    public ListIterator(LinkedList<T> list) {
      current = list.getRoot(); 
      root = current;
      onRoot = true;
    } 
      
    // Returns false if next element does not exist 
    public boolean hasNext() {
      if(current == null) return false;
      if(onRoot) return true;
      return current != root; 
    } 
      
    // Return current data and update pointer 
    public T next() {
      T value = current.value; 
      current = current.next; 
      if(onRoot == true) {
        onRoot = false;
      }
      return value; 
    } 
      
    // Implement if needed 
    public void remove() {
      throw new UnsupportedOperationException(); 
    } 
  } 

  // Stores the pointer to the first node in the linked list
  private Node<T> root;

  // Stores the size of the LinkedList.
  private int size;

  public LinkedList() {
    this.root = null;
    this.size = 0;
  }

  /**
   * Returns the pointer to the root node.
   */
  protected Node<T> getRoot() {
    return this.root;
  }

  /**
   * Adds element at the end of the LinkedList.
   *
   * Complexity: O(1)
   */
  public void addEnd(T elem) {
    Node<T> newNode = new Node(elem, null, null);
    if(root == null) {
      root = newNode;
      root.next = newNode;
      root.prev = newNode;
    } else {
      Node<T> leaf = root.prev;
      leaf.next = newNode;
      newNode.prev = leaf;
      newNode.next = root;
      root.prev = newNode;
    }
    this.size++;
  }
  
  /**
   * Add an element or update it.
   * Returns the replaced value, otherwise returns null.
   *
   * Complexity: O(n)
   */
  public T addOrUpdate(T elem) {
    Node<T> newNode = new Node(elem, null, null);
    if(root == null) {
      root = newNode;
      root.next = newNode;
      root.prev = newNode;
      this.size++;
    } else {
      Node<T> currentNode = root;

      do {
        boolean isEqual = currentNode.value.equals(elem);
        if(isEqual) {
          T removedElem = currentNode.value;

          currentNode.value = null;
          currentNode.value = elem;
          return removedElem;
        }
        currentNode = currentNode.next;
      } while(currentNode!=root);

      this.addEnd(elem);
    }

    return null;
  }

  /**
   * Removes the given element from the LinkedList and returns it.
   * Returns `null` if element does not exist.
   *
   * Complexity: O(n)
   */
  public boolean remove(T value) {
    if(root == null) return false;

    Node<T> currentNode = root;

    do {
      if(currentNode.value.equals(value)) {
        Node<T> prevNode = currentNode.prev;
        prevNode.next = currentNode.next;

        if(currentNode == root) {
          if(currentNode.next == root) {
            root = null;
          } else {
            root = currentNode.next;
          }
        }

        currentNode.next.prev = prevNode;

        currentNode.value = null;
        currentNode.next = null;
        currentNode.prev = null;
        currentNode = null;
        this.size--;
        return true;
      }
      currentNode = currentNode.next;
    } while(currentNode!=root);
    return false;
  }

  /**
   * Find and return the value of an element. 
   */
  public T find(T elem) {
    if(root == null) return null;

    Node<T> currentNode = root;

    if(root.value.equals(elem)) {
      return root.value;
    }

    currentNode = currentNode.next;

    while(currentNode != root){
      if(currentNode.value.equals(elem)) {
        return currentNode.value;
      }
      currentNode = currentNode.next;
    }

    return null;
  }

  /**
   * Returns the ListIterator.
   */
  public Iterator<T> iterator() {
    return new ListIterator<T>(this); 
  } 

  /**
   * Empties the LinkedList.
   */
  public void clear() {
    Node<T> currentNode = root;

    if(root == null) return;

    do {
      Node<T> nextNode = currentNode.next;

      currentNode.value = null;
      currentNode.next = null;
      currentNode.prev = null;
      currentNode = null;
      currentNode = nextNode;
    } while(currentNode != root);
  }

  /**
   * Returns the size of the LinkedList.
   */
  public int size() {
    return this.size;
  }
}

