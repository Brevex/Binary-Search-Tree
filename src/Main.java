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
        tree.insertElement(5);
        tree.insertElement(100);
        tree.insertElement(900);
        tree.insertElement(10000);
        tree.insertElement(700);

        tree.printTree(1);
    }
}
