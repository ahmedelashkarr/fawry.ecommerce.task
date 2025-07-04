package model.Cart;

import exception.OutOfStockProductException;
import model.Product.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<>();
    private double total =  0.0;

    public Cart(List<CartItem> items) {
        this.items = items;
        setTotal();
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }


    public void setTotal() {
        this.total = items.stream()
                .map(CartItem::getTotalPrice)
                .reduce(0.0 , Double::sum);
    }


    public List<CartItem> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }



    public boolean isEmpty() {
        return items.isEmpty();
    }
}
