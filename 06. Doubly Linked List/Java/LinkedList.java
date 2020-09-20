/**
 * LinkedList is a class to represent Doubly Linked List data structure 
 * with various functionalities.
*/
class LinkedList<T> {

  /**
   * Class to represend a node for the LinkedList
   */
  class Node<T> {
    public T value;
    public Node<T> next;
    public Node<T> prev;

    public Node(T value) {
      this.value = value;
    }

    public Node(T value, Node<T> prev, Node<T> next) {
      this.value = value;
      this.next = next;
      this.prev = prev;
    }
  }

  // Stores the pointer to the first node in the linked list
  private Node<T> root;

  // Stores the pointer to the last node in the linked list
  private Node<T> leaf;

  public LinkedList() {
    this.root = null;
    this.leaf = null;
  }

  /**
   * Adds element at the end of the LinkedList.
   *
   * Complexity: O(1)
   */
  public void addEnd(T elem) {
    Node<T> newNode = new Node(elem, leaf, null);
    if(root == null) {
      root = newNode;
      leaf = root;
    } else {
      leaf.next = newNode;
      leaf = newNode;
    }
  }
  
  /**
   * Adds element at the start of the LinkedList.
   *
   * Complexity: O(1)
   */
  public void addStart(T elem) {
    Node<T> newNode = new Node(elem, null, root);
    if(root == null) {
      root = newNode;
      leaf = root;
    } else {
      root.prev = newNode;
      root = newNode;
    }
  }

  /**
   * Removes element from the start of the LinkedList and returns it.
   * Returns `null` if LinkedList is empty.
   *
   * Complexity: O(1)
   */
  public T removeStart() {
    if(root == null) {
      return null;
    } else {
      Node<T> removedNode = root;
      T removedElem = removedNode.value;
      root = root.next;

      if(root == null) {
        leaf = null;
      } else {
        root.prev = null;
      }

      removedNode.value = null;
      removedNode.next = null;
      removedNode.prev = null;
      removedNode = null;

      return removedElem;
    }
  }

  /**
   * Removes element from the end of the LinkedList and returns it.
   * Returns `null` if LinkedList is empty.
   *
   * Complexity: O(1)
   */
  public T removeEnd() {
    if(root == null) {
      return null;
    } else {
      Node<T> currentNode = leaf;
      Node<T> preNode = leaf.prev;

      T removedElem = currentNode.value;
      if(preNode != null) {
        preNode.next = null;
      }

      leaf = preNode;

      if(leaf == null) {
        root = null;
      }

      currentNode.value = null;
      currentNode.next = null;
      currentNode.prev = null;
      currentNode = null;

      return removedElem;
    }
  }

  /**
   * Removes the given element from the LinkedList and returns it.
   * Returns `null` if element does not exist.
   *
   * Complexity: O(n)
   */
  public boolean remove(T value) {
    Node<T> currentNode = root;
    Node<T> prevNode = null;
    while(currentNode != null) {
      if(currentNode.value == value) {
        prevNode.next = currentNode.next;

        if(currentNode.next != null) {
          currentNode.next.prev = prevNode;
        }

        currentNode.value = null;
        currentNode.next = null;
        currentNode.prev = null;
        currentNode = null;
        return true;
      }
      prevNode = currentNode;
      currentNode = currentNode.next;
    }
    return false;
  }

  /**
   * Displays the LinkedList.
   *
   * Complexity: O(n)
   */
  public void display() {
    Node<T> currentNode = root;
    while(currentNode != null) {
      System.out.println(currentNode.value.toString());
      currentNode = currentNode.next;
    }
    System.out.println();
  }
}
