import java.util.*;

// Encapsulation for Product
class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return price * quantity; }

    @Override
    public String toString() {
        return "Product: " + name + ", Price: " + price + ", Quantity: " + quantity;
    }
}

// Polymorphism for Discounts
abstract class Discount {
    public abstract double applyDiscount(double amount, List<Product> products);
}

class FestiveDiscount extends Discount {
    @Override
    public double applyDiscount(double amount, List<Product> products) {
        return amount * 0.90; // 10% off
    }
}

class BulkDiscount extends Discount {
    @Override
    public double applyDiscount(double amount, List<Product> products) {
        for (Product p : products) {
            if (p.getQuantity() > 5) {
                return amount * 0.80; // 20% off
            }
        }
        return amount; // no discount
    }
}

// Payment Interface
interface Payment {
    void pay(double amount);
}

class CardPayment implements Payment {
    @Override
    public void pay(double amount) {
        System.out.println("Total Amount Payable: " + amount);
    }
}

// Main Shopping Cart
public class index {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        List<Product> cart = new ArrayList<>();

        // Taking input
        for (int i = 0; i < n; i++) {
            String[] data = sc.nextLine().split(" ");
            String name = data[0];
            double price = Double.parseDouble(data[1]);
            int qty = Integer.parseInt(data[2]);
            cart.add(new Product(name, price, qty));
        }

        String discountType = sc.nextLine().trim().toLowerCase();

        // Calculate total
        double total = 0;
        for (Product p : cart) {
            System.out.println(p);
            total += p.getTotalPrice();
        }

        // Apply discount (polymorphism)
        Discount discount;
        if (discountType.equals("festive")) {
            discount = new FestiveDiscount();
        } else {
            discount = new BulkDiscount();
        }

        double discountedTotal = discount.applyDiscount(total, cart);

        // Payment
        Payment payment = new CardPayment();
        payment.pay(discountedTotal);
    }
}
