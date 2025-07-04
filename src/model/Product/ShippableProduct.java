package model.Product;

import interFace.Shippable;

public class ShippableProduct extends Product implements Shippable {

    private double weight;

    public ShippableProduct(String name ,  double price, int quantity , double weight) {
        super(name, price, quantity);
        this.weight = weight;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public double getWeight() {
        return 0;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
