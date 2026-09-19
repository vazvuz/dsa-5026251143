package lw01.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<PrintJob> jobs = new ArrayList<>();

        // Membaca file jobs.txt (cek di package maupun root)
        File file = new File("src/lw01/prelab/jobs.txt");
        if (!file.exists()) {
            file = new File("jobs.txt");
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String type = scanner.next();
                String id = scanner.next();
                int pages = scanner.nextInt();

                if (type.equalsIgnoreCase("MONO")) {
                    jobs.add(new MonoPrint(id, pages));
                } else if (type.equalsIgnoreCase("COLOUR")) {
                    jobs.add(new ColourPrint(id, pages));
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File jobs.txt tidak ditemukan: " + e.getMessage());
            return;
        }

        // Cetak ringkasan menggunakan polimorfisme
        for (PrintJob job : jobs) {
            System.out.println(job.summary());
        }
    }
}