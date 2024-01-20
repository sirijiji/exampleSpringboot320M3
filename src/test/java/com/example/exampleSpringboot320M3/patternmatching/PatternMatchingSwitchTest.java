package com.example.exampleSpringboot320M3.patternmatching;

import com.example.exampleSpringboot320M3.annotation.ParallelizedTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ParallelizedTest
class PatternMatchingSwitchTest {

    interface Fruit{}

    class Apple implements Fruit{
        @Override
        public String toString() {
            return "apple";
        }
    }
    class Orange implements Fruit{
        @Override
        public String toString() {
            return "orange";
        }
    }
    class Mango implements Fruit{
        @Override
        public String toString() {
            return "mango";
        }
    }

    // Prior to Java 21
    static String formatter(Object obj) {
        String formatted = "unknown";
        if (obj instanceof Integer i) {
            formatted = String.format("int %d", i);
        } else if (obj instanceof Long l) {
            formatted = String.format("long %d", l);
        } else if (obj instanceof Double d) {
            formatted = String.format("double %f", d);
        } else if (obj instanceof String s) {
            formatted = String.format("String %s", s);
        }
        return formatted;
    }
    // As of Java 21
    static String formatterPatternSwitch(Object obj) {
        return switch (obj) {
            case Integer i -> String.format("int %d", i);
            case Long l    -> String.format("long %d", l);
            case Double d  -> String.format("double %f", d);
            case String s  -> String.format("String %s", s);
            default        -> obj.toString();
        };
    }
    @Test
    void testpatternmatching_java21(){

        List<Fruit> fruits = new ArrayList<>();
        fruits.add(new Apple());
        fruits.add(new Orange());
        fruits.add(new Mango());

        for (Fruit fruit : fruits) {
            switch (fruit) {
                case Apple apple -> log.info(apple.toString());// => apple
                case Orange orange -> log.info(orange.toString());// => orange
                case Mango mango -> log.info(mango.toString());// => mango
                default -> throw new IllegalStateException("Unexpected value: " + fruit);
            }
        }
    }

    @Test
    void patternmatching_instanceof(){

        Fruit apple = new Apple();

        if(apple instanceof Apple apple1){
            log.info(apple1.toString());
        }

    }

    // Prior to Java 21
    static void testFooBarOld(String s) {
        if (s == null) {
            log.info("Oops!");
            return;
        }
        switch (s) {
            case "Foo", "Bar" -> log.info("Great");
            default           -> log.info("Ok");
        }
    }

    // As of Java 21
    static void testFooBarNew(String s) {
        switch (s) {
            case null         -> log.info("Oops");
            case "Foo", "Bar" -> log.info("Great");
            default           -> log.info("Ok");
        }
    }

    // As of Java 21
    static void testStringOld(String response) {
        switch (response) {
            case null -> { }
            case String s -> {
                if (s.equalsIgnoreCase("YES"))
                    log.info("You got it");
                else if (s.equalsIgnoreCase("NO"))
                    log.info("Shame");
                else
                    log.info("Sorry?");
            }
        }
    }
    // As of Java 21
    static void testStringNew(String response) {
        switch (response) {
            case null -> { }
            case String s
                    when s.equalsIgnoreCase("YES") -> {
                log.info("You got it");
            }
            case String s
                    when s.equalsIgnoreCase("NO") -> {
                log.info("Shame");
            }
            case String s -> {
                log.info("Sorry?");
            }
        }
    }

    // As of Java 21
    sealed interface S permits A, B, C {}
    final class A implements S {}
    final class B implements S {}
    record C(int i) implements S {}    // Implicitly final

    static int testSealedExhaustive(S s) {
        return switch (s) {
            case A a -> 1;
            case B b -> 2;
            case C c -> 3;
        };
    }

    // As of Java 21
    sealed interface CardClassification permits Suit, Tarot {}
    public enum Suit implements CardClassification { CLUBS, DIAMONDS, HEARTS, SPADES }
    final class Tarot implements CardClassification {}

    static void exhaustiveSwitchWithBetterEnumSupport(CardClassification c) {
        switch (c) {
            case Suit.CLUBS -> {
                log.info("It's clubs");
            }
            case Suit.DIAMONDS -> {
                log.info("It's diamonds");
            }
            case Suit.HEARTS -> {
                log.info("It's hearts");
            }
            case Suit.SPADES -> {
                log.info("It's spades");
            }
            case Tarot t -> {
                log.info("It's a tarot");
            }
        }
    }
}
