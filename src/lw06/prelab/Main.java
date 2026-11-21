package lw06.prelab;

import java.util.Scanner;

public class Main {

    public static Node insert143(Node root, int data) {
        if (root == null) {
            return new Node(data);
        }

        if (data < root.data) {
            root.left = insert143(root.left, data);
        } else if (data > root.data) {
            root.right = insert143(root.right, data);
        }

        return root;
    }

    public static boolean search143(Node root, int target) {
        if (root == null) {
            return false;
        }

        if (target == root.data) {
            return true;
        } else if (target < root.data) {
            return search143(root.left, target);
        } else {
            return search143(root.right, target);
        }
    }

    public static void preorder(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    public static void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.data + " ");
            inorder(root.right);
        }
    }

    public static void postorder(Node root) {
        if (root != null) {
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));

        Node root143 = null;
        int lastNum = 0;

        // Di treenumbers.txt: semua angka di-insert, dan angka paling akhir jadi target search
        while (sc.hasNextInt()) {
            int val = sc.nextInt();
            lastNum = val;
            root143 = insert143(root143, val);
        }

        int target = lastNum;

        // Karena angka terakhir (40) ikut ke-insert di loop atas, hapus node target kalau mau murni dari baris 1,
        // atau lebih aman baca per baris biar persis format file treenumbers.txt:
        sc.close();

        // Baca ulang per baris agar insert hanya dari baris 1 dan target dari baris 2
        Scanner scLine = new Scanner(Main.class.getResourceAsStream("treenumbers.txt"));
        root143 = null;
        if (scLine.hasNextLine()) {
            String line1 = scLine.nextLine().trim();
            Scanner lineSc = new Scanner(line1);
            while (lineSc.hasNextInt()) {
                root143 = insert143(root143, lineSc.nextInt());
            }
            lineSc.close();
        }
        if (scLine.hasNextInt()) {
            target = scLine.nextInt();
        }
        scLine.close();

        System.out.print("Preorder : ");
        preorder(root143);
        System.out.println();

        System.out.print("Inorder : ");
        inorder(root143);
        System.out.println();

        System.out.print("Postorder : ");
        postorder(root143);
        System.out.println();

        System.out.println("Search " + target + " : " + search143(root143, target));
    }
}