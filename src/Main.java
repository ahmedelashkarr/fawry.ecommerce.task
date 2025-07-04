import model.Accounts.Customer;
import model.Cart.Cart;
import model.Cart.CartItem;
import model.Product.Product;
import model.Product.ShippableProductHasExpire;

import service.CheckoutService;

import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer("ahmed" , 1000);
        Product product = new ShippableProductHasExpire("Cheese" , 100 , 4, LocalDate.of(2025 , 7 ,5) , 200);
        Product product2 = new ShippableProductHasExpire("Biscuits" , 150 , 4 , LocalDate.of(2025 , 7 ,5) , 700);

        CartItem cartItem = new CartItem(product , 2);
        CartItem cartItem2 = new CartItem(product2 , 1);

        Cart cart = new Cart(List.of(cartItem , cartItem2));


        try {
            CheckoutService.checkout(customer , cart);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());;
        }
    }
}