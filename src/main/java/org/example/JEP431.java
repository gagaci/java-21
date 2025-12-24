package org.example;

import java.util.*;


public class JEP431 {
    public static void main(String[] args) {

///
/// Before JDK 21, Java's collection interfaces were missing a unified way to access the first and last elements,
///  even though many collections have a defined order.
///

        List<String> list = List.of("a", "b", "c");
        Set<String> sortedSet = new TreeSet<>(List.of("a", "b", "c"));
        Deque<String> deque = new ArrayDeque<>(List.of("a", "b", "c"));

        String firstList = list.get(0);                    // List way
        String firstSet = sortedSet.iterator().next();     // Set way
        String firstDeque = deque.getFirst();              // Deque way

        // Getting the LAST element - even worse!
        String lastList = list.get(list.size() - 1);       // List way
        //        String lastSet = ???;                               // No easy way!
        // You'd have to use: sortedSet.reversed().iterator().next()
        String lastDeque = deque.getLast();


        /// They introduced three new collections
        // 1. SequencedCollection base interface
        // 2. SequencedSet (for sets with order)
        // 3. SequencedMap (for maps)

        List<String> list2 = new ArrayList<>(List.of("a", "b", "c"));
        Deque<String> deque2 = new ArrayDeque<>(List.of("a", "b", "c"));
        LinkedHashSet<String> set2 = new LinkedHashSet<>(List.of("a", "b", "c"));

        // All use the same methods now!
        System.out.println(list2.getFirst());   // "a"
        System.out.println(deque2.getFirst());  // "a"
        System.out.println(set2.getFirst());    // "a"

        System.out.println(list2.getLast());    // "c"
        System.out.println(deque2.getLast());   // "c"
        System.out.println(set2.getLast());     // "c"

        // Adding elements
        list2.addFirst("f");
        list2.addLast("d");

        System.out.println(list2);


        // Reversed view, it was a bit weird that java didn't have reverse order before, as it is a must-have feature

        List<String> reversed = list2.reversed();

        System.out.println(list2); // original
        System.out.println(reversed);


    }
}
