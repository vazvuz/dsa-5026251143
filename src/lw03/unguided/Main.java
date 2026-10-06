package lw03.unguided;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Set<String> registered = new HashSet<>();
        Set<String> attended = new HashSet<>();
        List<String> results = new ArrayList<>();
        
        int rejected = 0;
        
        int absent = 0;

        Scanner input1 = new Scanner(Main.class.getResourceAsStream("registrations.txt"));
        while (input1.hasNextLine()) {
            String id = input1.nextLine();
            if (registered.contains(id)) {
            } else {
                registered.add(id);
            }
        }
        
        input1.close();
        Scanner input2 = new Scanner(Main.class.getResourceAsStream("checkins.txt"));
        while (input2.hasNextLine()) {
            String id = input2.nextLine();
            if (registered.contains(id)) {
                if (attended.contains(id)) {
                    
                    results.add(id + ": Rejected (already checked in)");
                    
                    rejected++;
                    
                } else {
                    results.add(id + ": Checked in");
                    attended.add(id);
                }
            } else {
                
                results.add(id + ": Rejected (not registered)");
                
                rejected++;   
            }
        }
        
        input2.close();
        
        System.out.println("===== Event Check-In Results =====");
        
        for (int i = 0; i < results.size(); i++) {
            
            String res = results.get(i);
            System.out.println(res);
        }

        absent = registered.size() - attended.size();
        
        System.out.println();
        System.out.println("===== Final Event Summary =====");
        System.out.println("Registered students: " + registered.size());
        System.out.println("Successful check-ins: " + attended.size());
        System.out.println("Absent students: " + absent);
        System.out.println("Rejected attempts: " + rejected);
        
    }
}