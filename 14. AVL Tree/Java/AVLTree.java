import java.util.ArrayDeque;
import java.util.Stack;

/**
 * AVLTree is a class to represent a AVL Tree data structure with various
 * functionalities.
 */
class AVLTree<T extends Comparable> {
  // Stores the number of nodes in the tree.
  private int size;

  /**
   * TreeNode represents a single node of the AVLTree.
   */
  class TreeNode {
    public T value;
    public TreeNode left;
    public TreeNode right;

    public int bf;
    public int height;

    public TreeNode() {
      this.value = null;
      this.left = null;
      this.right = null;
      this.bf = 0;
      this.height = 0;
    }

    public TreeNode(T value) {
      this.value = value;
      this.left = null;
      this.right = null;
      this.bf = 0;
      this.height = 0;
    }

    public TreeNode(T value, TreeNode left, TreeNode right) {
      this.value = value;
      this.left = left;
      this.right = right;
    }
  }

  /**
   * Internal class to contain details of node remove operation.
   */
  private class NodeRemoveDetails {
    public TreeNode root;
    public boolean isRemoved;

    public NodeRemoveDetails(TreeNode root, boolean isRemoved) {
      this.root = root;
      this.isRemoved = isRemoved;
    }
  }

  private class TraversalNode {
    public TreeNode node;
    public boolean isVisited;
    public boolean visitedLeft;

    public TraversalNode(TreeNode node) {
      this.node = node;
      this.isVisited = false;
      this.visitedLeft = false;
    }
  }

  // The root node of the AVLTree.
  private TreeNode root;

  /**
   * Public constructor to initialize AVLTree.
   */
  public AVLTree() {
    this.size = 0;
    this.root = null;
  }

  /**
   * Internal method to update the height and balance factor of the given node.
   *
   * Complexity: O(1)
   */
  private void updateMetaData(TreeNode node) {
    int leftHeight = this.calcHeight(node.left);
    int rightHeight = this.calcHeight(node.right);

    node.height = 1 + Math.max(leftHeight, rightHeight);
    node.bf = rightHeight - leftHeight;
  }

  /**
   * Internal method to recursively insert the newNode in the given root.
   *
   * Complexity: O(log(n))
   */
  private TreeNode insert(TreeNode root, TreeNode newNode) {
    if (root == null) {
      root = newNode;
    } else if (root.value.compareTo(newNode.value) > 0) {
      root.left = insert(root.left, newNode);
    } else {
      root.right = insert(root.right, newNode);
    }

    this.updateMetaData(root);
    return balance(root);
  }

  /**
   * Internal method to balance the given AVLTree.
   *
   * Complexity: O(1)
   */
  private TreeNode balance(TreeNode root) {
    if (root == null)
      return null;

    if (root.bf <= 1 && root.bf >= -1)
      return root;

    if (root.bf > 1) {
      if (root.right.bf >= 0) {
        root = rotateLeft(root);
      } else {
        root.right = rotateRight(root.right);
        root = rotateLeft(root);
      }
    } else if (root.bf < 1) {
      if (root.left.bf <= 0) {
        root = rotateRight(root);
        updateMetaData(root);
      } else {
        root.left = rotateLeft(root.left);
        root = rotateRight(root);
      }
    }

    return root;
  }

  /**
   * Internal method to rotate the given AVLTree to the left.
   *
   * Complexity: O(1)
   */
  private TreeNode rotateLeft(TreeNode node) {
    TreeNode rightNode = node.right;

    node.right = rightNode.left;
    rightNode.left = node;

    updateMetaData(node);
    updateMetaData(rightNode);

    return rightNode;
  }

  /**
   * Internal method to rotate the given AVLTree to the right.
   *
   * Complexity: O(1)
   */
  private TreeNode rotateRight(TreeNode node) {
    TreeNode leftNode = node.left;

    node.left = leftNode.right;
    leftNode.right = node;

    updateMetaData(node);
    updateMetaData(leftNode);

    return leftNode;
  }

  /**
   * Add a node in the AVLTree.
   *
   * Complexity: O(log(n))
   */
  public void insert(T value) {
    TreeNode newNode = new TreeNode(value);
    root = insert(root, newNode);
    this.size++;
  }

  /**
   * Find the given value in the AVLTree.
   *
   * Complexity: O(log(n))
   */
  public T find(T value) {
    if (root == null) {
      return null;
    }

    TreeNode currentNode = root;
    while (currentNode != null && !currentNode.value.equals(value)) {
      if (currentNode.value.compareTo(value) > 0) {
        currentNode = currentNode.left;
      } else {
        currentNode = currentNode.right;
      }
    }

    if (currentNode == null)
      return null;

    return currentNode.value;
  }

  /**
   * Internal method to find the maximum in the given AVLTree.
   *
   * Complexity: O(log(n))
   */
  private T findMax(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode currentNode = root, prevNode = null;
    while (currentNode != null) {
      prevNode = currentNode;
      currentNode = currentNode.right;
    }

    if (prevNode == null)
      return root.value;

    return prevNode.value;
  }

  /**
   * Internal method to find the min in the given AVLTree.
   *
   * Complexity: O(log(n))
   */
  private T findMin(TreeNode root) {
    if (root == null) {
      return null;
    }

    TreeNode currentNode = root, prevNode = null;
    while (currentNode != null) {
      prevNode = currentNode;
      currentNode = currentNode.left;
    }

    if (prevNode == null)
      return root.value;

    return prevNode.value;
  }

  /**
   * Internal method to recursively remove a node starting from a given root node.
   *
   * Complexity: O(log(n))
   */
  private NodeRemoveDetails removeRecursive(TreeNode node, T value) {
    if (node == null)
      return new NodeRemoveDetails(null, false);

    boolean isRemoved = false;

    int comp = node.value.compareTo(value);

    if (comp > 0) {
      NodeRemoveDetails details = removeRecursive(node.left, value);
      node.left = details.root;
      isRemoved = details.isRemoved;
    } else if (comp < 0) {
      NodeRemoveDetails details = removeRecursive(node.right, value);
      node.right = details.root;
      isRemoved = details.isRemoved;
    } else {
      if (node.left == null) {
        return new NodeRemoveDetails(node.right, true);
      } else if (node.right == null) {
        return new NodeRemoveDetails(node.left, true);
      } else {
        isRemoved = true;

        if (node.left.height > node.right.height) {

          T successorValue = findMax(node.left);
          node.value = successorValue;

          node.left = (removeRecursive(node.left, successorValue)).root;

        } else {

          T successorValue = findMin(node.right);
          node.value = successorValue;

          node.right = (removeRecursive(node.right, successorValue)).root;
        }
      }
    }

    updateMetaData(node);
    return new NodeRemoveDetails(balance(node), isRemoved);
  }

  /**
   * Public remove method to remove a given value from AVLTree.
   *
   * Complexity: O(log(n))
   */
  public boolean remove(T value) {
    NodeRemoveDetails details = this.removeRecursive(root, value);
    root = details.root;
    if (details.isRemoved)
      this.size--;
    return details.isRemoved;
  }

  /**
   * Private method to calculate the height from a given node.
   *
   * Complexity: O(n)
   */
  private int calcHeight(TreeNode node) {
    if (node == null)
      return -1;

    return 1 + Math.max(this.calcHeight(node.left), this.calcHeight(node.right));
  }

  /**
   * Public method to return the height of AVLTree.
   *
   * Complexity: O(n)
   */
  public int height() {
    return this.calcHeight(root);
  }

  /**
   * Public method to display the structure of the AVLTree.
   *
   * Complexity: O(n)
   */
  public void displayStructure() {
    if (root == null) {
      System.out.println("\nTree is empty.");
      return;
    }

    ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
    TreeNode nullNode = new TreeNode();
    queue.add(root);

    int height = this.height() + 1;
    int index = 1;
    int lastLevel = -1;
    boolean isLeft = true;

    while (!queue.isEmpty()) {
      TreeNode currentNode = queue.poll();
      int currentLevel = (int) (Math.log(index) / Math.log(2));
      int spaces = ((int) Math.pow(2, (height - currentLevel))) * 3;
      isLeft = false;

      if (spaces > 0)
        spaces = spaces - 1;

      if (currentLevel > lastLevel) {
        isLeft = true;
        System.out.println();
      }

      if (currentLevel >= height) {
        break;
      }

      for (int i = 0; i < spaces; i++)
        System.out.print(" ");

      if (currentNode == nullNode) {
        System.out.printf("%s", "N");

        queue.add(nullNode);
        queue.add(nullNode);
      } else {
        System.out.printf("%d", currentNode.value);

        if (currentNode.left != null)
          queue.add(currentNode.left);
        else
          queue.add(nullNode);

        if (currentNode.right != null)
          queue.add(currentNode.right);
        else
          queue.add(nullNode);
      }

      spaces++;

      for (int i = 0; i < spaces; i++)
        System.out.print(" ");

      lastLevel = currentLevel;
      index++;
      isLeft = !isLeft;
    }

    System.out.println("Size: " + this.size);
    this.printPreOrder();
    this.printInOrder();
    this.printPostOrder();
    this.printLevelOrder();
    System.out.println();
  }

  /**
   * Internal method to print pre-order.
   */
  private void preOrder(TreeNode root) {
    if (root == null)
      return;

    Stack<TraversalNode> stack = new Stack<TraversalNode>();
    stack.push(new TraversalNode(root));

    while (!stack.empty()) {
      TraversalNode currentNode = stack.peek();
      if (!currentNode.isVisited) {
        System.out.print(currentNode.node.value + ", ");
      } else if (!currentNode.visitedLeft) {
        currentNode.visitedLeft = true;
        if (currentNode.node.left != null)
          stack.push(new TraversalNode(currentNode.node.left));
      } else {
        stack.pop();
        if (currentNode.node.right != null)
          stack.push(new TraversalNode(currentNode.node.right));
      }

      currentNode.isVisited = true;
    }
  }

  /**
   * Internal method to print post-order.
   */
  private void postOrder(TreeNode root) {
    if (root == null)
      return;

    Stack<TraversalNode> stack = new Stack<TraversalNode>();
    stack.push(new TraversalNode(root));

    while (!stack.empty()) {
      TraversalNode currentNode = stack.peek();
      if (!currentNode.visitedLeft) {
        currentNode.visitedLeft = true;
        if (currentNode.node.left != null)
          stack.push(new TraversalNode(currentNode.node.left));
      } else if (!currentNode.isVisited) {
        currentNode.isVisited = true;
        if (currentNode.node.right != null)
          stack.push(new TraversalNode(currentNode.node.right));
      } else {
        System.out.print(currentNode.node.value + ", ");
        stack.pop();
      }
    }
  }

  /**
   * Internal method to print in-order.
   */
  private void inOrder(TreeNode root) {
    if (root == null)
      return;

    Stack<TraversalNode> stack = new Stack<TraversalNode>();
    stack.push(new TraversalNode(root));

    while (!stack.empty()) {
      TraversalNode currentNode = stack.peek();
      if (!currentNode.isVisited) {
        currentNode.isVisited = true;
        if (currentNode.node.left != null)
          stack.push(new TraversalNode(currentNode.node.left));
      } else {
        System.out.print(currentNode.node.value + ", ");
        stack.pop();
        if (currentNode.node.right != null)
          stack.push(new TraversalNode(currentNode.node.right));
      }
    }
  }

  /**
   * Internal method to print level-order.
   */
  private void levelOrder(TreeNode root) {
    if (root == null)
      return;

    ArrayDeque<TreeNode> queue = new ArrayDeque<TreeNode>();
    queue.add(root);

    while (!queue.isEmpty()) {
      TreeNode currentNode = queue.poll();
      System.out.print(currentNode.value + ", ");

      if (currentNode.left != null)
        queue.add(currentNode.left);

      if (currentNode.right != null)
        queue.add(currentNode.right);
    }
  }

  /**
   * Public method to print pre-order.
   */
  public void printPreOrder() {
    System.out.print("Preorder: ");
    this.preOrder(root);
    System.out.println();
  }

  /**
   * Public method to print post-order.
   */
  public void printPostOrder() {
    System.out.print("Postorder: ");
    this.postOrder(root);
    System.out.println();
  }

  /**
   * Public method to print in-order.
   */
  public void printInOrder() {
    System.out.print("Inorder: ");
    this.inOrder(root);
    System.out.println();
  }

  /**
   * Public method to print level-order.
   */
  public void printLevelOrder() {
    System.out.print("LevelOrder: ");
    this.levelOrder(root);
    System.out.println();
  }
}
