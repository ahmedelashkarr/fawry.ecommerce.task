package service;

import exception.CartEmptyException;
import exception.ExpiredProductException;
import exception.InsufficientCustomerBalanceException;
import exception.OutOfStockProductException;
import interFace.Shippable;
import model.Accounts.Customer;
import model.Cart.Cart;
import model.Cart.CartItem;
import model.Product.ExpirableProduct;

import java.time.LocalDate;

import java.util.List;

public class CheckoutService {
    private static final int ShippingCost = 30;
    public static void checkout(Customer customer, Cart cart) {
        cartIsEmpty(cart);
        quantityIsValid(cart.getItems());
        CustomerBalanceIsValid(cart.getTotal(), customer.getBalance());
        List<ExpirableProduct> expirableProducts = cart.getItems().stream()
                .filter(item -> item.getProduct() instanceof ExpirableProduct)
                .map(item -> (ExpirableProduct) item.getProduct())
                .toList();
        ExpiredProductIsValid(expirableProducts);

        customer.setBalance(customer.getBalance() - cart.getTotal() - ShippingCost);

        printReceiptData(cart, customer);
    }

    private static void printReceiptData(Cart cart, Customer customer) {
        List<CartItem> cartItemList = cart.getItems().stream()
                .filter(item -> item.getProduct() instanceof Shippable)
                .toList();
        System.out.println("** Shipment notice **");
        for (CartItem item : cartItemList) {
                double weight = ((Shippable) item.getProduct()).getWeight() * item.getQuantity();
                System.out.println(item.getQuantity() + "X " + item.getProduct().getName() + "\t" + weight + "g");
        }
        double totalWeight = cartItemList.stream().map(item -> ((Shippable) item.getProduct()).getWeight() * item.getQuantity())
                .reduce(0.0, Double::sum);
        System.out.println("Total package weight " + totalWeight + "g");
        System.out.println("\n** Checkout receipt **");
        for (CartItem item : cartItemList) {
                double price = item.getProduct().getPrice() * item.getQuantity() ;
                System.out.println(item.getQuantity() + "X " + item.getProduct().getName() + "\t" + price);

        }
        double subTotal = cartItemList.stream()
                .map(CartItem::getTotalPrice)
                .reduce(0.0, Double::sum);

        System.out.println("----------------------");
        System.out.println("Subtotal\t "+subTotal );
        System.out.println("Shipping\t " + ShippingCost);
        System.out.println("Amount\t" + (subTotal + ShippingCost));
        System.out.println("Customer Balance is "+customer.getBalance());

    }

    private static void quantityIsValid(List<CartItem> cartItemsList) {
        for (CartItem item : cartItemsList) {
            if (item.getQuantity() > item.getProduct().getQuantity())
                throw new OutOfStockProductException("%s OUT OF STOCK !!!".formatted(item.getProduct().getName()));
        }
    }

    private static void cartIsEmpty(Cart cart) {
        if (cart == null) throw new CartEmptyException("CART IS EMPTY !!!");
    }

    private static void CustomerBalanceIsValid(double totalOfItems, double amountOfCustomer) {
        if (totalOfItems > amountOfCustomer)
            throw new InsufficientCustomerBalanceException("BALANCE IS INSUFFICIENT !!!");
    }

    private static void ExpiredProductIsValid(List<ExpirableProduct> expirableProducts) {
        for (ExpirableProduct expirableProduct : expirableProducts) {
            if (LocalDate.now().isAfter(expirableProduct.getExpiryDate()))
                throw new ExpiredProductException("PRODUCT IS EXPIRED !!!");
        }
    }


}
