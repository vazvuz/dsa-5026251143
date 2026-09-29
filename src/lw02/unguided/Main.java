package lw02.unguided;

import java.util.*;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
       //buat linkedlist utnuk nyimpan data
        LinkedList<String[]> orders = new LinkedList<>();
        LinkedList<String[]> foodStock = new LinkedList<>();
        LinkedList<String[]> drinkStock = new LinkedList<>();
        LinkedList<String[]> successfulOrders = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedOrders = new Stack<>();

        //masukin stok drink and food
        foodStock.add(new String[]{"Bakso", "2"});
        foodStock.add(new String[]{"Sate", "1"});
        foodStock.add(new String[]{"Soto", "2"});

        drinkStock.add(new String[]{"EsTeh", "4"});
        drinkStock.add(new String[]{"EsJeruk", "2"});

        // buat anuin dari orders txt
        InputStream stream = Main.class.getResourceAsStream("orders.txt");
        if (stream == null) {
            stream = Main.class.getResourceAsStream("/orders.txt");
        }

        if (stream != null) {
            Scanner scanner = new Scanner(stream);
            while (scanner.hasNext()) {
                String name = scanner.next();
                String food = scanner.next();
                String drink = scanner.next();
                String table = scanner.next();
                
                orders.add(new String[]{name, food, drink, table});
            }
            scanner.close();
        }
        // masukin ke antrian queue
        queue.addAll(orders);
        //proses 
        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String name = order[0];
            String food = order[1];
            String drink = order[2];
            String table = order[3];
        //ngecek stok makanan
            String[] targetFood = null;
            if (!food.equals("-")) {
                for (String[] f : foodStock) {
                    if (f[0].equals(food)) {
                        targetFood = f;
                        break;
                    }
                }
            }
        //cek stok minumannn
            String[] targetDrink = null;
            if (!drink.equals("-")) {
                for (String[] d : drinkStock) {
                    if (d[0].equals(drink)) {
                        targetDrink = d;
                        break;
                    }
                }
            }
        //cek ketersediaannya
            boolean foodAvailable = food.equals("-") || (targetFood != null && Integer.parseInt(targetFood[1]) > 0);
            boolean drinkAvailable = drink.equals("-") || (targetDrink != null && Integer.parseInt(targetDrink[1]) > 0);
        //loginya ngurang ngurangin
            if (foodAvailable && drinkAvailable) {
                if (!food.equals("-") && targetFood != null) {
                    int currentStock = Integer.parseInt(targetFood[1]);
                    targetFood[1] = String.valueOf(currentStock - 1);
                }
                if (!drink.equals("-") && targetDrink != null) {
                    int currentStock = Integer.parseInt(targetDrink[1]);
                    targetDrink[1] = String.valueOf(currentStock - 1);
                }
                
                successfulOrders.add(order);
            } else {

                failedOrders.push(order);
            }
        }
    //yang diprint
        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successfulOrders) {
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }

        System.out.println("=== Remaining Food Stock ===");
        for (String[] f : foodStock) {
            System.out.println(f[0] + ": " + f[1]);
        }

        System.out.println("=== Remaining Drink Stock ===");
        for (String[] d : drinkStock) {
            System.out.println(d[0] + ": " + d[1]);
        }

        System.out.println("=== Failed Orders ===");
        while (!failedOrders.isEmpty()) {
            String[] order = failedOrders.pop();
            System.out.println(order[0] + " " + order[1] + " " + order[2] + " " + order[3]);
        }
    }
}