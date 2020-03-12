package ru.oz.streams;

import com.xpinjection.patterns.iterator.canonical.LineIterator;
import com.xpinjection.patterns.iterator.canonical.Text;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {

    public static Text getText(Object obj) {
        return new Text() {
            @Override
            public LineIterator lineIterator() {
                return null;
            }
        };
    }

    public static void main(String[] args) {

        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.FISH),    // свиниа
                new Dish("beef", false, 700, Dish.Type.MEAT),    // говядина
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french", false, 530, Dish.Type.OTHER),
                new Dish("rice", false, 350, Dish.Type.OTHER),   // рис
                new Dish("season", true, 120, Dish.Type.OTHER),  // сезонное
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),  // креветка
                new Dish("salmon", false, 450, Dish.Type.FISH)   // лосось
        );


        List<String> names = menu.stream()
                .map(Dish::getName)
                .collect(Collectors.toList());

        System.out.println(names);


        Function<Object, Text> func = Main::getText;

        MyFunc func2 = Main::getText;


    }

}

@FunctionalInterface
interface MyFunc {
    Text get(java.lang.Object x);

}