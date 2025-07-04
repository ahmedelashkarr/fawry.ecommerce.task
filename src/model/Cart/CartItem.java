package model.Cart;

import model.Product.Product;

public class CartItem {
    private Product product;
    private int quantity;
    private double totalPrice;

    public CartItem( Product product,int quantity) {
        this.quantity = quantity;
        this.product = product;
        setTotalPrice();
    }


    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice() {
        this.totalPrice = product.getPrice() * quantity;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
