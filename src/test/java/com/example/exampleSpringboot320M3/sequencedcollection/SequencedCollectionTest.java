package com.example.exampleSpringboot320M3.sequencedcollection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;
class SequencedCollectionTest {

    @Test
    void getfirstElement_list(){
        List<String> lists = new ArrayList<>();
        lists.add("firstElem");
        lists.add("secondElem");
        lists.add("thirdElem");
        String firstElem = lists.get(0); //Before java 21
        String firstElemWithjdk21 = lists.getFirst(); //After java 21
        String last = lists.getLast(); // => thirdElem
        System.out.println(last);
        lists.addFirst("rootElem"); // => rootElem
        System.out.println(lists.getFirst());
        List<String> reversed = lists.reversed(); // => [thirdElem, secondElem, firstElem, rootElem]
        System.out.println(reversed);

        System.out.println(firstElemWithjdk21); // => firstElem


    }

    @Test
    void getfirstElement_deque(){
        Deque<String> dequeStrings = new ArrayDeque<>();
        dequeStrings.add("first");
        String firstElement = dequeStrings.iterator().next(); //Before java 21

//        dequeStrings.getLast()
    }


    @Test
    void sortedset_getfirstElement(){
        SortedSet<String> sortedSet = new TreeSet<>();
        sortedSet.add("apple");
        sortedSet.add("mango");
        sortedSet.add("orange");

        String next = sortedSet.iterator().next(); //before java 21
        System.out.println(next); // => apple

        String first = sortedSet.getFirst(); // after java 21
        System.out.println(first); // => apple
    }


}
