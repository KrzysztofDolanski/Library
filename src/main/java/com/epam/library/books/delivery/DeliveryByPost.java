package com.epam.library.books.delivery;

public class DeliveryByPost implements Delivery{


    @Override
    public boolean addressAccessibility(String address) {

        return false;
    }

    @Override
    public void collectDeliverDetails() {

    }
}
