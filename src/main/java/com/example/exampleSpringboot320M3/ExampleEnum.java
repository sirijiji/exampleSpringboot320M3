package com.example.exampleSpringboot320M3;

import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExampleEnum {

    TEST1("test %s test %s test"),
    TEST2("test %s test %s test %s test %s test");//\"(?:[^"]*%s[^"]*%s[^"]*%s[^"]*)\"

    @Getter
    private final String label;
}
