package service;


import model.Cart.Cart;
import model.Cart.CartItem;
import model.Product.Product;

import java.util.List;

public class CartService {
    public static void addItemToCart(Product product, int quantity , Cart cart) {

//            if (!cart.getItems().isEmpty()) {
//                for (int i = 0; i < cart.getItems().size(); i++) {
//                    if (cart.getItems().get(i).getProduct().getId() == product.getId()) {
//                        cart.getItems().get(i).setQuantity(cart.getItems().get(i).getQuantity() + quantity);
//                    } else
//                        cart.getItems().add(new CartItem(product, quantity));
//                }
//            } else cart.getItems().add(new CartItem(product, quantity));
//
//            cart.setTotal(cart.getTotal()+ product.getPrice() * quantity);
//        }else throw new OutOfStockProductException();

        CartItem cartItem = new CartItem(product,quantity);
        List<CartItem> cartItems = cart.getItems();
        cartItems.add(cartItem);
        cart.setItems(cartItems);
    }
//    public void decreaseProduct(Product product, int quantity ,Cart cart ) {
//        for (CartItem item : cart.getItems()) {
//            int def = item.getQuantity() - quantity;
//            if (item.getProduct().getId() == product.getId()) {
//                if (def < 0) {
//                    total -= item.getProduct().getPrice() * item.getQuantity();
//                    item.setQuantity(0);
//                } else {
//                    item.setQuantity(def);
//                    total -= item.getProduct().getPrice() * def;
//                }
//            } else
//                System.out.println("No product here match this product to decrease");
//        }
//    }
//    private static void quantityIsValid(int quantity ,int quantityOfProduct ){
//        if (quantity > quantityOfProduct) throw new OutOfStockProductException("Sorry Product out of stock :(");
//    }
//    private static void cartIsEmpty(Cart cart){
//        if (cart == null )throw new CartEmptyException("Cart is Empty :(");
//    }
}
