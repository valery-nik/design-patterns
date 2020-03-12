package ru.oz.patterns.skeleton;

public class VendingManager {
    public static void main(String[] args) {

//        https://dzone.com/articles/favour-skeletal-interface-in-java
        Ivending candy = new CandyVending();
        Ivending drink = new DrinkVending();
        candy.process();
        System.out.println("*********************");
        drink.process();
        if (drink instanceof VendingService) {
            VendingService vs = (VendingService) drink;
            vs.service();
        }
    }
}

