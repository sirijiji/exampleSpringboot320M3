package com.example.exampleSpringboot320M3.record;

import com.example.exampleSpringboot320M3.annotation.ParallelizedTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

@Slf4j
@ParallelizedTest
public class RecordTest {

    record Box<T>(T t) {}

    static void test1(Box<Box<String>> bbs) {
        if (bbs instanceof Box<Box<String>>(Box(var s))) {
           log.info("String " + s);
        }
    }

    @Test
    @Execution(ExecutionMode.CONCURRENT)
    void check(){
        Box<Box<String>> b = new Box<>(new Box<>("test"));
        test1(b);
    }
}
