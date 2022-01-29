package com.techelevator;

public class Candy extends Product {
    public Candy(String name, double price, int items_quantity) {
        super(name, price, items_quantity);
    }

    @Override
    public void displayMessage() {
        System.out.println("Munch Munch, Yum!");
    }

}
