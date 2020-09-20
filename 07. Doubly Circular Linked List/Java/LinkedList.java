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

  public LinkedList() {
    this.root = null;
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
  }
  
  /**
   * Adds element at the start of the LinkedList.
   *
   * Complexity: O(1)
   */
  public void addStart(T elem) {
    Node<T> newNode = new Node(elem, null, null);
    if(root == null) {
      root = newNode;
      root.next = newNode;
      root.prev = newNode;
    } else {
      newNode.next = root;
      newNode.prev = root.prev;
      root.prev.next = newNode;
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

      if(root == removedNode) {
        root = null;
      } else {
        root.prev = removedNode.prev;
        if(root.prev != null) {
          root.prev.next = root;
        }
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
      Node<T> currentNode = root.prev;
      T removedElem = currentNode.value;

      if(currentNode == root) {
        root = null;
      } else {
        Node<T> preNode = currentNode.prev;
        preNode.next = root;
        root.prev = preNode;
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
    if(root == null) return false;

    Node<T> currentNode = root;

    do {
      if(currentNode.value == value) {
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
        return true;
      }
      currentNode = currentNode.next;
    } while(currentNode!=root);
    return false;
  }

  /**
   * Displays the LinkedList.
   *
   * Complexity: O(n)
   */
  public void display() {
    if(root != null) {
      Node<T> currentNode = root;
      System.out.println(currentNode.value.toString());

      currentNode = currentNode.next;
      while(currentNode != root) {
        System.out.println(currentNode.value.toString());
        currentNode = currentNode.next;
      }
      System.out.println();
    }
  }
}
