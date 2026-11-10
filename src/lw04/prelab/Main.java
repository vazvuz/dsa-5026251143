package lw04.prelab;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(Main.class.getResourceAsStream("branches.txt"));

        // 1. Membaca  branches
        int n = scanner.nextInt();
        int[] branches = new int[n];
        
        for (int i = 0; i < n; i++) {
            int tempVal = scanner.nextInt(); // simpan ke variabel temporary dulu
            branches[i] = tempVal;
        }

        // 2. Membaca  target
        int m = scanner.nextInt();
        int[] targets = new int[m];
        
        for (int i = 0; i < m; i++) {
            targets[i] = scanner.nextInt();
        }
        
        scanner.close();

        // 3. Proses Insertion Sort
        int maxPasses = 3; // variabel terpisah untuk batas pass
        for (int i = 1; i <= maxPasses && i < n; i++) {
            int key = branches[i];
            int j = i - 1;
            
            while (j >= 0 && branches[j] < key) {
                branches[j + 1] = branches[j];
                j = j - 1; // nulis j = j - 1 bukannya j--
            }
            branches[j + 1] = key;
        }

        // 4. Cetak isi array setelah 3 passes
        for (int i = 0; i < n; i++) {
            System.out.print(branches[i]);
            if (i < n - 1) {
                System.out.print(" "); // cetak spasi antar angka
            }
        }
        System.out.println(); // pindah baris

        // 5. Linear Search untuk mencari target
        for (int t = 0; t < m; t++) {
            int target = targets[t];
            boolean isFound = false; // flag penanda ketemu atau enggak
            int indexKetemu = -1;

            for (int i = 0; i < n; i++) {
                if (branches[i] == target) {
                    isFound = true;
                    indexKetemu = i;
                    break;
                }
            }

            if (isFound) {
                System.out.println(target + ": FOUND at index " + indexKetemu);
            } else {
                System.out.println(target + ": NOT FOUND");
            }
        }
    }
}