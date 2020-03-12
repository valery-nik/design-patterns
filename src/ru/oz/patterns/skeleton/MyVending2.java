package ru.oz.patterns.skeleton;

public class MyVending2 extends AbstractVending {

    public MyVending2(Ivending decoratedIvending) {
        super(decoratedIvending);
    }

    public void start() {
        start();
        decoratedIvending.start();
        System.out.println("Start Vending machine");
    }

    @Override
    public void chooseProduct() {

    }

    public void stop() {
        System.out.println("Stop Vending machine");
    }

    public void process() {
        start();
        chooseProduct();
        stop();
    }
}
