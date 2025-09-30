import java.util.*;

// Encapsulation for Product
class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }

    public int getQuantity(){
        return quantity;
    }

    public double getTotalPrice(){
        return price * quantity;
    }
}

// Discount polymorphism
abstract class Discount {
    public abstract double applyDiscount(ArrayList<Product> products);
}

class FestiveDiscount extends Discount {
    public double applyDiscount(ArrayList<Product> products){
        double total = 0;
        for(Product p : products){
            total += p.getTotalPrice();
        }
        return total * 0.9; // 10% off
    }
}

class BulkDiscount extends Discount {
    public double applyDiscount(ArrayList<Product> products){
        double total = 0;
        for(Product p : products){
            if(p.getQuantity() > 5){
                total += p.getTotalPrice() * 0.8; // 20% off for bulk
            } else {
                total += p.getTotalPrice();
            }
        }
        return total;
    }
}

// Payment interface
interface Payment {
    void pay(double amount);
}

// Implementation of Payment
class Pay implements Payment {
    public void pay(double amount){
        System.out.printf("Total Amount Payable: %.2f\n", amount);
    }
}

public class index2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine(); // consume newline

        ArrayList<Product> cart = new ArrayList<>();

        for(int i=0; i<n; i++){
            String line = sc.nextLine();
            String[] parts = line.split(" ");
            String name = parts[0];
            double price = Double.parseDouble(parts[1]);
            int qty = Integer.parseInt(parts[2]);

            cart.add(new Product(name, price, qty));
        }

        String discountType = sc.nextLine().toLowerCase();

        // Print product details
        for(Product p : cart){
            System.out.printf("Product: %s, Price: %.2f, Quantity: %d\n", p.getName(), p.getPrice(), p.getQuantity());
        }

        Discount discount;
        if(discountType.equals("festive")){
            discount = new FestiveDiscount();
        } else {
            discount = new BulkDiscount();
        }

        double finalAmount = discount.applyDiscount(cart);

        Payment payment = new Pay();
        payment.pay(finalAmount);
    }
}
