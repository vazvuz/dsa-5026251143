package lw02.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> transactionList = new LinkedList<>();

        try {
            File file = new File("src/lw02/prelab/transactions.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\\s+");
                    transactionList.add(parts);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File transactions.txt tidak ditemukan!");
            return;
        }

        // 1. Simpan data customer
        LinkedList<String[]> customerList = new LinkedList<>();
        for (String[] tx : transactionList) {
            String name = tx[0];
            boolean isExist = false;

            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    isExist = true;
                    break;
                }
            }

            if (!isExist) {
                customerList.add(new String[]{name, "0"});
            }
        }

        // 2. Pindahkan ke Queue
        Queue<String[]> transactionQueue = new LinkedList<>();
        for (String[] tx : transactionList) {
            transactionQueue.add(tx);
        }

        // 3. Stack untuk transaksi gagal
        Stack<String[]> failedStack = new Stack<>();

        // 4. Proses Queue
        while (!transactionQueue.isEmpty()) {
            String[] tx = transactionQueue.poll();
            String name = tx[0];
            String type = tx[1];
            int amount = Integer.parseInt(tx[2]);

            // Cari customer
            String[] targetCustomer = null;
            for (String[] cust : customerList) {
                if (cust[0].equals(name)) {
                    targetCustomer = cust;
                    break;
                }
            }

            if (targetCustomer != null) {
                int currentBalance = Integer.parseInt(targetCustomer[1]);

                if (type.equals("DEPOSIT")) {
                    currentBalance += amount;
                    targetCustomer[1] = String.valueOf(currentBalance);
                } else if (type.equals("WITHDRAW")) {
                    if (amount > currentBalance) {
                        failedStack.push(tx); // Masuk Stack jika saldo tidak cukup
                    } else {
                        currentBalance -= amount;
                        targetCustomer[1] = String.valueOf(currentBalance);
                    }
                }
            }
        }

        // 5. Cetak Output sesuai format
        System.out.println("=== Final Balances ===");
        for (String[] cust : customerList) {
            System.out.println(cust[0] + ": " + cust[1]);
        }

        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] failedTx = failedStack.pop();
            System.out.println(failedTx[0] + " " + failedTx[1] + " " + failedTx[2]);
        }
    }
}