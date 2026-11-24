package lw06.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        BinarySearchTree tree = new BinarySearchTree();

        for (int i = 0; i < 9; i++) {
            int val = read.nextInt();
            tree.insert(val);
        }

  
        int target = read.nextInt();
        read.close();

        System.out.print("Preorder : ");
        tree.preOrder();

        System.out.print("Inorder : ");
        tree.inOrder();

        System.out.print("Postorder: ");
        tree.postOrder();

        System.out.println("Search " + target + ": " + tree.search(target));
        System.out.println("Nodes: " + tree.countNodes());
        System.out.println("Leaves : " + tree.countLeaves());
    }
}