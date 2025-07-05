package service;

import exception.*;
import interFace.Shippable;
import model.Accounts.Customer;
import model.Cart.Cart;
import model.Cart.CartItem;
import model.Product.ExpirableProduct;
import model.Product.UnShippableProduct;
import java.time.LocalDate;
import java.util.List;

public class CheckoutService {
    private static final int ShippingCost = 30;

    public static void checkout(Customer customer, Cart cart) {

        //Validation
        cartIsEmpty(cart);
        CustomerIsValid(customer);
        quantityIsValid(cart.getItems());
        CustomerBalanceIsValid(cart.getTotal(), customer.getBalance());

        //get Expire Products
        List<ExpirableProduct> expirableProducts = cart.getItems().stream()
                .filter(item -> item.getProduct() instanceof ExpirableProduct)
                .map(item -> (ExpirableProduct) item.getProduct())
                .toList();
        ExpiredProductIsValid(expirableProducts);

        //after all rights we reduce the balance
        customer.setBalance(customer.getBalance() - cart.getTotal() - ShippingCost);

        //print receipt data
        printReceiptData(cart, customer);
    }

    private static void printReceiptData(Cart cart, Customer customer) {
        // get shipping product to out shipping details
        List<CartItem> cartItemListShippable = cart.getItems().stream()
                .filter(item -> item.getProduct() instanceof Shippable)
                .toList();

        //Shipment notice
        System.out.println("** Shipment notice **");
        for (CartItem item : cartItemListShippable) {
            double weight = ((Shippable) item.getProduct()).getWeight() * item.getQuantity();
            System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + "\t" + weight + "g");
        }

        //calculate total weight
        double totalWeight = cartItemListShippable.stream().map(item -> ((Shippable) item.getProduct()).getWeight() * item.getQuantity())
                .reduce(0.0, Double::sum);
        //print it
        System.out.println("Total package weight " + (totalWeight /1000) + "kg");
        System.out.println("\n** Checkout receipt **");
        for (CartItem item : cartItemListShippable) {
            double price = item.getProduct().getPrice() * item.getQuantity();
            if (item.getQuantity() > 0)
                System.out.println(item.getQuantity() + "x " + item.getProduct().getName() + "\t" + price);

        }
        //calculate subTotal
        double subTotal = cartItemListShippable.stream()
                .map(CartItem::getTotalPrice)
                .reduce(0.0, Double::sum);

        //print total price after adding shipping
        System.out.println("----------------------");
        System.out.println("Subtotal\t :" + subTotal);
        System.out.println("Shipping\t :" + ShippingCost);
        System.out.println("Amount\t\t :" + (subTotal + ShippingCost));

        //calculate UnShipping product if exist to tell the customer details all the order and his balance
        List<CartItem> cartItemListUnShippable = cart.getItems().stream()
                .filter(item -> item.getProduct() instanceof UnShippableProduct)
                .toList();
        if (!cartItemListUnShippable.isEmpty()) {
            System.out.println("----------------------\n** UnShipping Product **");
            for (CartItem cartItem : cartItemListUnShippable) {
                double price = cartItem.getProduct().getPrice() * cartItem.getQuantity();
                System.out.println(cartItem.getQuantity() + "x " + cartItem.getProduct().getName() + "\t" + price);
            }
            double totalForUnShippingProduct = cartItemListUnShippable.stream()
                    .map(CartItem::getTotalPrice)
                    .reduce(0.0, Double::sum);
            System.out.println("Total for UnShipping Product " + totalForUnShippingProduct);

        }

        System.out.println("----------------------");
        System.out.println("Customer Balance : " + customer.getBalance());

    }

    // ** Helper Methods Validation **
    private static void cartIsEmpty(Cart cart) {
        if (cart == null) throw new CartEmptyException("CART IS EMPTY !!!");
    }

    private static void CustomerBalanceIsValid(double totalOfItems, double amountOfCustomer) {
        if (totalOfItems > amountOfCustomer)
            throw new InsufficientCustomerBalanceException("YOUR BALANCE %f IS NOT ENOUGH !!!".formatted(amountOfCustomer));
    }

    private static void CustomerIsValid(Customer customer) {
        if (customer == null)
            throw new CartEmptyException("CUSTOMER IS NULL !!!");
    }

    private static void quantityIsValid(List<CartItem> cartItemsList) {
        for (CartItem item : cartItemsList) {
            if (item.getQuantity() > item.getProduct().getQuantity())
                throw new OutOfStockProductException("%s OUT OF STOCK !!!".formatted(item.getProduct().getName()));
        }
    }

    private static void ExpiredProductIsValid(List<ExpirableProduct> expirableProducts) {
        for (ExpirableProduct expirableProduct : expirableProducts) {
            if (LocalDate.now().isAfter(expirableProduct.getExpiryDate()))
                throw new ExpiredProductException("%s IS EXPIRED !!!".formatted(expirableProduct.getName()));
        }
    }


}
