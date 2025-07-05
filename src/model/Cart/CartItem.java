package model.Cart;

import exception.ProductNullException;
import exception.QuantityException;
import model.Product.Product;

public class CartItem {
    private Product product;
    private int quantity;
    private double totalPrice;

    public CartItem( Product product,int quantity) {
        setProduct( product);
        setQuantity(quantity);
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
        if(product == null)
            throw new ProductNullException("PRODUCT IS NULL !!!");
        this.product = product;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if(quantity < 0)
            throw new QuantityException("QUANTITY INVALID (Must be more than or equal 0) !!!");
        this.quantity = quantity;
    }


}
