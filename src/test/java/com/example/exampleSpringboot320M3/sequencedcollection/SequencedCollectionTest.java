package com.example.exampleSpringboot320M3.sequencedcollection;

import com.example.exampleSpringboot320M3.annotation.ParallelizedTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.*;

@Slf4j
@ParallelizedTest
class SequencedCollectionTest {

    @Test
    void getfirstElement_list(){
        log.info("getfirstElement_list");
        List<String> lists = new ArrayList<>();
        lists.add("firstElem");
        lists.add("secondElem");
        lists.add("thirdElem");
        String firstElem = lists.get(0); //Before java 21
        String firstElemWithjdk21 = lists.getFirst(); //After java 21
        String last = lists.getLast(); // => thirdElem
        log.info(last);
        lists.addFirst("rootElem"); // => rootElem
        log.info(lists.getFirst());
        List<String> reversed = lists.reversed(); // => [thirdElem, secondElem, firstElem, rootElem]

        log.info(firstElemWithjdk21); // => firstElem


    }

    @Test
    void getfirstElement_deque(){
        log.info("getfirstElement_deque");
        Deque<String> dequeStrings = new ArrayDeque<>();
        dequeStrings.add("first");
        String firstElement = dequeStrings.iterator().next(); //Before java 21

//        dequeStrings.getLast()
    }


    @Test
    void sortedset_getfirstElement(){
        log.info("sortedset_getfirstElement");
        SortedSet<String> sortedSet = new TreeSet<>();
        sortedSet.add("apple");
        sortedSet.add("mango");
        sortedSet.add("orange");

        String next = sortedSet.iterator().next(); //before java 21
        log.info(next); // => apple

        String first = sortedSet.getFirst(); // after java 21
        log.info(first); // => apple
    }


}
