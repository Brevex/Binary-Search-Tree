package binarysearchtree;

public class BinaryTree
{
    private Node root;
    private int counter = 0;

    // ----------------------- Constructor -------------------------

    public BinaryTree() { this.root = null; }

    // -------------------- Conventional Methods -------------------

    // updates the amount of nodes in the right and left subtrees (recursive method).

    private void updateSubtreeNodeAmount(Node currentNode)
    {
        if (currentNode == null) { return; }

        if (currentNode.getLeft() != null)
        {
            updateSubtreeNodeAmount(currentNode.getLeft());
            currentNode.setSizeLeft(currentNode.getLeft().getSizeLeft() + currentNode.getLeft().getSizeRight() + 1);
        }
        else { currentNode.setSizeLeft(0); }

        if (currentNode.getRight() != null)
        {
            updateSubtreeNodeAmount(currentNode.getRight());
            currentNode.setSizeRight(currentNode.getRight().getSizeLeft() + currentNode.getRight().getSizeRight() + 1);
        }
        else { currentNode.setSizeRight(0); }
    }

    // find the place where we want to add a new node to keep the tree sorted (recursive method).

    private Node recursiveInsert(Node currentNode, int value)
    {
        /*
        Null: reached the appropriate position to insert the new node.
        New value is smaller : places in the left subtree.
        New value is greater : places in the right subtree.
        Else : value already exists.
        */

        if (currentNode == null)
        {
            currentNode = new Node(value);
        }
        else if (value < currentNode.getValue())
        {
            currentNode.setLeft(recursiveInsert(currentNode.getLeft(), value));
            updateSubtreeNodeAmount(currentNode);
        }
        else if (value > currentNode.getValue())
        {
            currentNode.setRight(recursiveInsert(currentNode.getRight(), value));
            updateSubtreeNodeAmount(currentNode);
        }

        return currentNode;
    }

    public void insertElement(int value) { root = recursiveInsert(root, value); }

    // check if the tree contains a specific value (recursive method).

    private boolean recursiveSearch(Node currentNode, int value)
    {
        // searching for the value by comparing it to the value in the current node.

        if (currentNode == null) { return false; }

        if (value == currentNode.getValue()) { return true; }

        if (value < currentNode.getValue())
        {
            return recursiveSearch(currentNode.getLeft(), value);
        }
        else { return recursiveSearch(currentNode.getRight(), value); }
    }

    public boolean searchElement(int value) { return recursiveSearch(root, value); }

    // find the node to delete from the tree (recursive method).

    private Node recursiveDelete(Node currentNode, int value)
    {
        // Value not found, nothing to delete.

        if (currentNode == null) { return null; }

        // Node to delete found, handle the three cases:

        if (value == currentNode.getValue())
        {
            // Case 1: Node has no children.

            if (currentNode.getLeft() == null && currentNode.getRight() == null) { return null; }

            // Case 2: Node has one child (left child).

            if (currentNode.getLeft() == null) { return currentNode.getRight(); }

            // Case 2: Node has one child (right child).

            if (currentNode.getRight() == null) { return currentNode.getLeft(); }

            // Case 3: Node has two children, find the smallest value in the right subtree.

            if (currentNode.getLeft() != null && currentNode.getRight() != null)
            {
                int smallestValue = findSmallestValue(currentNode.getRight());

                // Replace with the smallest value delete the smallest node in the right subtree.

                currentNode.setValue(smallestValue);
                currentNode.setRight(recursiveDelete(currentNode.getRight(), smallestValue));
                return currentNode;
            }
        }

        /*
        Value is smaller : search in the left subtree.
        Value is greater : search in the right subtree.
        */

        if (value < currentNode.getValue())
        {
            currentNode.setLeft(recursiveDelete(currentNode.getLeft(), value));
        }
        else
        {
            currentNode.setRight(recursiveDelete(currentNode.getRight(), value));
        }

        updateSubtreeNodeAmount(currentNode);
        return currentNode;
    }

    public void deleteElement(int value) { root = recursiveDelete(root, value); }

    // --------------------- Specific Methods ----------------------

    // (1) returns the nth element (counting from 1) of the path in ABB order (symmetrical order).

    public int nthElement(int n)
    {
        if (root == null || n <= 0) { return -1; } // Invalid value.

        counter = 0; // restarting the counter.

        return findNthElement(root, n).getValue();
    }

    // (2) returns the position occupied by an element x in an in-order path symmetrical in ABB (counting from 1).

    public int position(int x)
    {
        if (root == null || x <= 0) { return -1; } // Invalid value.

        counter = 0; // restarting the counter.

        return findPositionInOrder(root, x);
    }

    // (3) returns the element that contains the median of the ABB.

    public int median()
    {
        if (root == null) { return -1; } // Invalid value.

        int totalElements = countNodes(root);

        // If the ABB has an even amount of elements, return the smaller of the two median elements.

        if (totalElements % 2 == 0)
        {
            int median1 = nthElement(totalElements / 2);
            int median2 = nthElement((totalElements / 2) + 1);

            return Math.min(median1, median2);
        }
        else
        {
            return nthElement(totalElements / 2);
        }
    }

    // (4) returns the arithmetic mean of the nodes of the tree that x is the root of.

    public double mean(Node x)
    {
        if (root == null) { return 0.0; } // empty root.

        int Sum = calculateSum(x);
        int totalElements = countNodes(x);

        // calculates the mean and avoids division by 0

        if (totalElements > 0)
        {
            return (double) Sum / totalElements;
        }
        else { return 0.0; }
    }

    // (5) return if the tree is full or not

    private boolean isFullTree(Node root)
    {
        if (root == null) { return true; } // an empty tree is full.

        // A leaf node is considered full.

        if (root.getLeft() == null && root.getRight() == null)
        {
            return true;
        }

        // Checks whether both the left and right subtrees are full trees.

        if (root.getLeft() != null && root.getRight() != null)
        {
            return isFullTree(root.getLeft()) && isFullTree(root.getRight());
        }

        return false;
    }

    public boolean isFull() { return isFullTree(root); }

    // (6) return if the tree is complete or not

    public boolean isComplete()
    {
        if (root == null) { return true; } // an empty tree is complete

        int height = treeHeight(root);
        int totalElements = countNodes(root);

        return (int) Math.pow(2, height - 1) <= totalElements && totalElements <= (int) Math.pow(2, height) - 1;
    }

    // (7) returns a String that contains the traversal of the tree in a pre-order.

    private String preOrder(Node currentNode)
    {
        if (currentNode == null) {  return ""; }

        String result = currentNode.getValue() + " ";
        result += preOrder(currentNode.getLeft());
        result += preOrder(currentNode.getRight());

        return result;
    }

    public String printPreOrder() { return preOrder(root).trim(); }

    // ----------------------- Helper Methods -----------------------

    // find the smallest node in the right subtree of the soon-to-be-deleted node (recursive method).

    private int findSmallestValue(Node root)
    {
        if (root.getLeft() == null)
        {
            return root.getValue();
        }
        else { return findSmallestValue(root.getLeft()); }
    }

    // find the nth element in the tree (recursive method).

    private Node findNthElement(Node currentNode, int n)
    {
        if (currentNode == null) { return null; } // element not found.

        // perform the traversal in symmetric order recursively.

        Node leftResult = findNthElement(currentNode.getLeft(), n);

        if (leftResult != null) { return leftResult; } // founded! (on the left).

        counter++;

        if (counter == n) { return currentNode; } // already founded.

        // continue searching in the right subtree.

        return findNthElement(currentNode.getRight(), n);
    }

    // find an element position in the tree (recursive method).

    private int findPositionInOrder(Node currentNode, int x)
    {
        if (currentNode == null) { return -1; } // element not found.

        // perform the traversal in symmetric order recursively.

        int leftResult = findPositionInOrder(currentNode.getLeft(), x);

        if (leftResult != -1) { return leftResult; } // founded! (on the left).

        counter++;

        if (currentNode.getValue() == x) { return counter; } // return position.

        // continue searching in the right subtree.

        return findPositionInOrder(currentNode.getRight(), x);
    }

    // makes the sum of the elements of the tree.

    private int calculateSum(Node currentNode)
    {
        if (currentNode == null) { return 0; }

        int leftSum = calculateSum(currentNode.getLeft());
        int rightSum = calculateSum(currentNode.getRight());

        return leftSum + rightSum + currentNode.getValue();
    }

    // calculate the amount of nodes

    private int countNodes(Node currentNode)
    {
        if (currentNode == null) { return 0; }

        return (1 + countNodes(currentNode.getLeft()) + countNodes(currentNode.getRight()));
    }

    // calculate the height of the tree

    private int treeHeight(Node node)
    {
        if (node == null) { return 0; }

        return 1 + Math.max(treeHeight(node.getLeft()), treeHeight(node.getRight()));
    }

    // -------------------- Getters and Setters --------------------

    public Node getRoot() { return root; }

    public void setRoot(Node root) { this.root = root; }
}
