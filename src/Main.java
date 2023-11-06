/*

  ------ Developed by Breno Barbosa & Marcos Vinícius ------
 Basic Data Structure II course project – Binary Search Tree.
                          - UFRN -

*/

import binarysearchtree.BinaryTree;
import binarysearchtree.Node;

public class Main
{
    public static void main(String[] args)
    {
        BinaryTree tree = new BinaryTree();

        tree.insertElement(10);
        tree.insertElement(5);
        tree.insertElement(15);
        tree.insertElement(3);
        tree.insertElement(7);
        tree.insertElement(14);
        tree.insertElement(16);
        tree.insertElement(2);
        tree.insertElement(4);
        tree.insertElement(13);

        Node root = tree.getRoot();
        int leftSize = tree.getLeftSubtreeSize(root);
        int rightSize = tree.getRightSubtreeSize(root);

        System.out.println("Left size: " + leftSize);
        System.out.println("Right size: " + rightSize);
    }
}
