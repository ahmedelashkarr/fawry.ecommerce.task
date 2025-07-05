package model.Product;

public class Product {

    protected int idCounter = 0;
    protected int id;
    protected String name;
    protected double price;
    protected int quantity;

    public Product( String name , double price, int quantity) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;
        idCounter++;
        id = idCounter;
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


}
