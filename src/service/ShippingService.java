package service;


import interFace.Shippable;
import model.Cart.Cart;
import model.Cart.CartItem;
import model.Product.Product;

import java.util.List;

public class ShippingService {
    public static double shipping(List<Shippable> shippableList){
        return shippableList.stream()
                .map(p -> p.getWeight() /30)
                .reduce(0.0 , Double::sum);
    }
}
