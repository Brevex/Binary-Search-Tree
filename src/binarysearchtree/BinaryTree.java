package binarysearchtree;

public class BinaryTree
{
    private Node root;

    // -------------- Constructor --------------

    public BinaryTree() { this.root = null; }

    // ---------- Conventional Methods ---------

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

    // ------------ Specific Methods -----------

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

    // ------------- Helper Methods ------------

    // find the smallest node in the right subtree of the soon-to-be-deleted node (recursive method).

    private int findSmallestValue(Node root)
    {
        if (root.getLeft() == null)
        {
            return root.getValue();
        }
        else { return findSmallestValue(root.getLeft()); }
    }

    // ---------- Getters and Setters ----------

    public Node getRoot() { return root; }

    public void setRoot(Node root) { this.root = root; }

    public int getLeftSubtreeSize(Node node)
    {
        if (node == null) { return 0; }

        return node.getSizeLeft();
    }

    public int getRightSubtreeSize(Node node)
    {
        if (node == null) { return 0; }

        return node.getSizeRight();
    }
}
