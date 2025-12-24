package org.example;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EncounterOrderDemo {
    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5);
        System.out.println("List (predictable):");
        for (int i = 0; i < 3; i++) {
            System.out.print("  Run " + (i + 1) + ": ");
            list.forEach(n -> System.out.print(n + " "));
            System.out.println();
        }

        Set<Integer> hashSet = new HashSet<>(List.of(1, 2, 3, 4, 5));
        System.out.println("\nHashSet (unpredictable):");
        for (int i = 0; i < 3; i++) {
            System.out.print("  Run " + (i + 1) + ": ");
            hashSet.forEach(n -> System.out.print(n + " "));
            System.out.println();
        }
    }

}

