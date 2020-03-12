package ru.oz.patterns.skeleton;

public abstract class AbstractVending implements Ivending {


    protected Ivending decoratedIvending;
    public AbstractVending(Ivending decoratedIvending) {
        super();
        this.decoratedIvending = decoratedIvending;
    }


    public void start() {
        System.out.println("Start Vending machine");
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
