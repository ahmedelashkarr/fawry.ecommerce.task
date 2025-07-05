import model.Accounts.Customer;
import model.Cart.Cart;
import model.Cart.CartItem;
import model.Product.Product;
import model.Product.ShippableProductHasExpire;

import model.Product.ShippableProductHasNotExpire;
import model.Product.UnShippableProduct;
import service.CheckoutService;

import java.time.LocalDate;
import java.util.List;

public class Main {
    /*
        Classes in product folder to create products
        -ShippingProductHasExpire
        -UnShippableProduct
     */
    public static void main(String[] args) {

        //create Customer
        Customer customer = new Customer("ahmed", 1000);
        //create products
        Product Cheese = new ShippableProductHasExpire("Cheese", 100, 4, LocalDate.of(2025, 7, 10), 200);
        Product Biscuits = new ShippableProductHasExpire("Biscuits", 150, 4, LocalDate.of(2025, 7, 10), 700);
        Product TV = new ShippableProductHasNotExpire("TV", 300, 1, 700);
        Product ScratchCard = new UnShippableProduct("ScratchCard", 50, 1);

        try {
            //create cartItem
            CartItem cartItem1 = new CartItem(Cheese, 2);
            CartItem cartItem2 = new CartItem(TV, 1);
            CartItem cartItem3 = new CartItem(Biscuits, 1);
            CartItem cartItem4 = new CartItem(ScratchCard, 1);

            Cart cart = new Cart(List.of(cartItem1, cartItem3 , cartItem4));

            CheckoutService.checkout(customer, cart);
        } catch (RuntimeException e) {
            System.out.println("\n" + e.getMessage());
            ;
        }
    }
}