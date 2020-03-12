package ru.oz.streams;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Dish {

    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;


    public enum Type {MEAT, FISH, OTHER}
}
