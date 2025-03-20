package com.example.exampleSpringboot320M3.scheduler;

import com.example.exampleSpringboot320M3.ExampleEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Disabled
public class SchedulerActions {


//    @Scheduled(fixedDelay = 10000)
    public void scheduled() {
        log.info("scheduled");
        log.info(String.format(ExampleEnum.TEST1.getLabel(), "1", "2"));
    }

}
