package lw04.unguided;

import java.util.*;

public class Main {

    static void bubbleSort143(int[] arr, int startPass, int endPass) {
        int n = arr.length;
        for (int i = startPass; i < endPass; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    static void binarySearch143(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int steps = 0;
        boolean ketemu = false;
        int indexKetemu = -1;

        while (low <= high) {
            int mid = (low + high) / 2;
            steps++;

            if (arr[mid] == target) {
                ketemu = true;
                indexKetemu = mid;
                break;
            } else if (arr[mid] > target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (ketemu) {
            System.out.println(target + ": FOUND at index " + indexKetemu + " (steps: " + steps + ")");
        } else {
            System.out.println(target + ": NOT FOUND (steps: " + steps + ")");
        }
    }

    static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i != arr.length - 1) {
                System.out.print(" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("parcels.txt"));

        int n = sc.nextInt();
        int[] paket = new int[n];
        for (int i = 0; i < n; i++) {
            paket[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] target = new int[m];
        for (int i = 0; i < m; i++) {
            target[i] = sc.nextInt();
        }
        sc.close();

        bubbleSort143(paket, 0, 2);
        
        int[] simpan = new int[n];
        for (int i = 0; i < n; i++) {
            simpan[i] = paket[i];
        }

        bubbleSort143(paket, 2, n - 1);

        System.out.println("==== After 2 Passes ====");
        printArray(simpan);
        
        System.out.println("==== Fully Sorted ====");
        printArray(paket);
        
        System.out.println("==== Search Results ====");
        for (int i = 0; i < m; i++) {
            binarySearch143(paket, target[i]);
        }
    }
}