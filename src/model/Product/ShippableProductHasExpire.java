package model.Product;

import interFace.Shippable;

import java.time.LocalDate;
import java.util.Date;

public class ShippableProductHasExpire extends ExpirableProduct implements Shippable {
    private double weight ;

    public ShippableProductHasExpire(String name, double price, int quantity, LocalDate expiryDate, double weight) {
        super(name, price, quantity, expiryDate);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
