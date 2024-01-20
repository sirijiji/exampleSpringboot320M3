package com.example.exampleSpringboot320M3;

import com.example.exampleSpringboot320M3.annotation.ParallelizedTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
@Slf4j
@ParallelizedTest
public class TestFeatureJdk21Test {


    @Test
    public void checkTEst(){
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    log.info(String.valueOf(i));
                    return i;
                });
            });
        }  // executor.close() is called implicitly, and waits
    }


}
