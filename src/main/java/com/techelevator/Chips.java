package com.techelevator;

public class Chips extends Product{
    public Chips(String name, double price, int items_quantity) {
        super(name, price, items_quantity);
    }

    @Override
    public void displayMessage() {
        System.out.println("Crunch Crunch, Yum!");

    }

}
