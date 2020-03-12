package ru.oz.patterns.skeleton;

public class DrinkVending extends VendingService  implements Ivending {
    private class AbstractVendingDelegator extends AbstractVending
    {
        @Override
        public void chooseProduct() {
            System.out.println("Produce diiferent soft drinks");
            System.out.println("Choose a type of soft drinks");
            System.out.println("pay for drinks");
            System.out.println("collect drinks");
        }
    }
    AbstractVendingDelegator delegator = new AbstractVendingDelegator();
    @Override
    public void start() {
        delegator.start();
    }
    @Override
    public void chooseProduct() {
        delegator.chooseProduct();
    }
    @Override
    public void stop() {
        delegator.stop();
    }
    @Override
    public void process() {
        delegator.process();
    }
}
