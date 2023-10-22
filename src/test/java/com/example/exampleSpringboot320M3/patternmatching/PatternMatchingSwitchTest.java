package com.example.exampleSpringboot320M3.patternmatching;

import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

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
                case Apple apple -> System.out.println(apple);// => apple
                case Orange orange -> System.out.println(orange);// => orange
                case Mango mango -> System.out.println(mango);// => mango
                default -> throw new IllegalStateException("Unexpected value: " + fruit);
            }
        }
    }

    @Test
    void patternmatching_instanceof(){

        Fruit apple = new Apple();

        if(apple instanceof Apple apple1){
            System.out.println(apple1);
        }

    }

    // Prior to Java 21
    static void testFooBarOld(String s) {
        if (s == null) {
            System.out.println("Oops!");
            return;
        }
        switch (s) {
            case "Foo", "Bar" -> System.out.println("Great");
            default           -> System.out.println("Ok");
        }
    }

    // As of Java 21
    static void testFooBarNew(String s) {
        switch (s) {
            case null         -> System.out.println("Oops");
            case "Foo", "Bar" -> System.out.println("Great");
            default           -> System.out.println("Ok");
        }
    }

    // As of Java 21
    static void testStringOld(String response) {
        switch (response) {
            case null -> { }
            case String s -> {
                if (s.equalsIgnoreCase("YES"))
                    System.out.println("You got it");
                else if (s.equalsIgnoreCase("NO"))
                    System.out.println("Shame");
                else
                    System.out.println("Sorry?");
            }
        }
    }
    // As of Java 21
    static void testStringNew(String response) {
        switch (response) {
            case null -> { }
            case String s
                    when s.equalsIgnoreCase("YES") -> {
                System.out.println("You got it");
            }
            case String s
                    when s.equalsIgnoreCase("NO") -> {
                System.out.println("Shame");
            }
            case String s -> {
                System.out.println("Sorry?");
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
                System.out.println("It's clubs");
            }
            case Suit.DIAMONDS -> {
                System.out.println("It's diamonds");
            }
            case Suit.HEARTS -> {
                System.out.println("It's hearts");
            }
            case Suit.SPADES -> {
                System.out.println("It's spades");
            }
            case Tarot t -> {
                System.out.println("It's a tarot");
            }
        }
    }
}
