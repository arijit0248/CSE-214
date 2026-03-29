
interface Purchase {
    double calculatePrice();
}

// Step 2: Concrete base purchase
class BasePurchase implements Purchase {
    private double price;

    public BasePurchase(double price) {
        this.price = price;
    }

    public double calculatePrice() {
        return price;
    }
}

// Step 3: Abstract Decorator
abstract class PurchaseDecorator implements Purchase {
    protected Purchase purchase;

    public PurchaseDecorator(Purchase purchase) {
        this.purchase = purchase;
    }

    public double calculatePrice() {
        return purchase.calculatePrice();
    }
}

// Step 4: Loyalty Discount (10%)
class LoyaltyDiscount extends PurchaseDecorator {
    public LoyaltyDiscount(Purchase purchase) {
        super(purchase);
    }

    public double calculatePrice() {
        return purchase.calculatePrice() * 0.90;
    }
}

// Step 5: Seasonal Discount (flat 100)
class SeasonalDiscount extends PurchaseDecorator {
    public SeasonalDiscount(Purchase purchase) {
        super(purchase);
    }

    public double calculatePrice() {
        return purchase.calculatePrice() - 100;
    }
}

// Step 6: High Value Discount (2%)
class HighValueDiscount extends PurchaseDecorator {
    public HighValueDiscount(Purchase purchase) {
        super(purchase);
    }

    public double calculatePrice() {
        return purchase.calculatePrice() * 0.98;
    }
}

// Step 7: Main class
public class Main {
    public static void main(String[] args) {
        double basePrice = 12000;

        boolean isPremium = true;
        boolean isSeason = true;

        // Create base purchase
        Purchase purchase = new BasePurchase(basePrice);

        // Apply discounts conditionally
        Purchase discountedPurchase = purchase;

        if (isPremium) {
            discountedPurchase = new LoyaltyDiscount(discountedPurchase);
        }

        if (isSeason) {
            discountedPurchase = new SeasonalDiscount(discountedPurchase);
        }

        if (basePrice > 10000) {
            discountedPurchase = new HighValueDiscount(discountedPurchase);
        }

        // Final price
        double finalPrice = discountedPurchase.calculatePrice();
        System.out.println("Final price after all discounts: " + finalPrice);
    }
}