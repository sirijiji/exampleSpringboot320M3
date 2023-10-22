package com.example.exampleSpringboot320M3.record;

import org.junit.jupiter.api.Test;

public class RecordTest {

    record Box<T>(T t) {}

    static void test1(Box<Box<String>> bbs) {
        if (bbs instanceof Box<Box<String>>(Box(var s))) {
            System.out.println("String " + s);
        }
    }

    @Test
    void check(){
        Box<Box<String>> b = new Box<>(new Box<>("test"));
        test1(b);
    }
}
