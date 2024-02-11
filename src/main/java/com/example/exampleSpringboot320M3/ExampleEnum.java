package com.example.exampleSpringboot320M3;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ExampleEnum {

    TEST1("test %s test %s test"),
    TEST2("test %s test %s test %s test %s test");//\"(?:[^"]*%s[^"]*%s[^"]*%s[^"]*)\"

    private final String label;
}
