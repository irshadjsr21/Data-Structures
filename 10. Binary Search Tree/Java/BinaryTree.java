import java.util.ArrayDeque;
import java.util.Stack;

/**
 * BinaryTree is a class to represent a Binary Search Tree data structure with
 * various functionalities.
 */
class BinaryTree<T extends Comparable> {
  // Stores the number of nodes in the tree.
  private int size;

  /**
   * TreeNode represents a single node of the BinaryTree.
   */
  class TreeNode {
    public T value;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
      this.value = null;
      this.left = null;
      this.right = null;
    }

    public TreeNode(T value) {
      this.value = value;
      this.left = null;
      this.right = null;
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

  // The root node of the BST.
  private TreeNode root;

  /**
   * Public constructor to initialize BinaryTree.
   */
  public BinaryTree() {
    this.size = 0;
    this.root = null;
  }

  /**
   * Add a node in the BinaryTree.
   *
   * Complexity: O(log(n))
   */
  public void insert(T value) {
    TreeNode newNode = new TreeNode(value);
    if (root == null) {
      root = newNode;
      this.size++;
    } else {
      TreeNode prevNode = root, currentNode = root;
      while (currentNode != null) {
        prevNode = currentNode;
        if (currentNode.value.compareTo(value) > 0) {
          currentNode = currentNode.left;
        } else {
          currentNode = currentNode.right;
        }
      }

      if (prevNode.value.compareTo(value) > 0) {
        prevNode.left = newNode;
      } else {
        prevNode.right = newNode;
      }
      this.size++;
    }
  }

  /**
   * Find the given value in the BST.
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
   * Internal method to remove a node starting from a given root node.
   *
   * Complexity: O(log(n))
   */
  private NodeRemoveDetails removeFrom(TreeNode node, T value) {
    if (node == null)
      return new NodeRemoveDetails(null, false);

    TreeNode currentNode = node;
    TreeNode prevNode = null;
    while (currentNode != null && !currentNode.value.equals(value)) {
      prevNode = currentNode;
      if (currentNode.value.compareTo(value) > 0) {
        currentNode = currentNode.left;
      } else {
        currentNode = currentNode.right;
      }
    }

    // When node is not found.
    if (currentNode == null)
      return new NodeRemoveDetails(node, false);

    // When node is a leaf node.
    if (currentNode.left == null && currentNode.right == null) {
      currentNode.value = null;

      if (prevNode == null) {
        node = null;
      } else if (prevNode.left == currentNode) {
        prevNode.left = null;
      } else {
        prevNode.right = null;
      }

      currentNode = null;
      return new NodeRemoveDetails(node, true);
    }

    // When node only has right sub tree.
    if (currentNode.left == null) {
      currentNode.value = null;

      if (prevNode == null) {
        node = currentNode.right;
      } else if (prevNode.left == currentNode) {
        prevNode.left = currentNode.right;
      } else {
        prevNode.right = currentNode.right;
      }

      currentNode.right = null;
      currentNode = null;
      return new NodeRemoveDetails(node, true);
    }

    // When node only has left sub tree.
    if (currentNode.right == null) {
      currentNode.value = null;

      if (prevNode == null) {
        node = currentNode.left;
      } else if (prevNode.left == currentNode) {
        prevNode.left = currentNode.left;
      } else {
        prevNode.right = currentNode.left;
      }

      currentNode.left = null;
      currentNode = null;
      return new NodeRemoveDetails(node, true);
    }

    // When node has both subtree.
    TreeNode leafNode = currentNode.left, leafPrevNode = currentNode;

    while (leafNode.right != null) {
      leafPrevNode = leafNode;
      leafNode = leafNode.right;
    }

    currentNode.value = leafNode.value;
    if (leafPrevNode.left == leafNode) {
      leafPrevNode.left = (this.removeFrom(leafNode, leafNode.value)).root;
    } else {
      leafPrevNode.right = (this.removeFrom(leafNode, leafNode.value)).root;
    }

    return new NodeRemoveDetails(node, true);
  }

  /**
   * Public remove method to remove a given value from BST.
   *
   * Complexity: O(log(n))
   */
  public boolean remove(T value) {
    NodeRemoveDetails details = this.removeFrom(root, value);
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
      return 0;

    return 1 + Math.max(this.calcHeight(node.left), this.calcHeight(node.right));
  }

  /**
   * Public method to return the height of BST.
   *
   * Complexity: O(n)
   */
  public int height() {
    return this.calcHeight(root);
  }

  /**
   * Public method to display the structure of the BST.
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

    int height = this.height();
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
    System.out.println();
  }

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

  public void printPreOrder() {
    System.out.print("Preorder: ");
    this.preOrder(root);
    System.out.println();
  }

  public void printPostOrder() {
    System.out.print("Postorder: ");
    this.postOrder(root);
    System.out.println();
  }

  public void printInOrder() {
    System.out.print("Inorder: ");
    this.inOrder(root);
    System.out.println();
  }
}
