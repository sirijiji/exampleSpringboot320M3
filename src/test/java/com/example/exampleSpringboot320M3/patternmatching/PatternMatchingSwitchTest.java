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

}
