package com.example.exampleSpringboot320M3;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class TestFeatureJdk21Test {


    @Test
    public void checkTEst(){
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000).forEach(i -> {
                executor.submit(() -> {
                    Thread.sleep(Duration.ofSeconds(1));
                    System.out.println(String.valueOf(i));
                    return i;
                });
            });
        }  // executor.close() is called implicitly, and waits
    }


}
