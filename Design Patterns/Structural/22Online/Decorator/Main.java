interface Purchase{
    double calculatePrice();
}

class BasePurchase implements Purchase{
    private double price;

    public BasePurchase(double price)
    {
        this.price = price;
    }

    @Override
    public double calculatePrice() {
        return price;
    }

}

abstract class PurchaseDecorator implements Purchase{
    protected Purchase purchase;
    public PurchaseDecorator(Purchase purchase)
    {
        this.purchase = purchase;
    }
    public double calculatePrice()
    {
        return purchase.calculatePrice();
    }
}

class LoyaltyDiscount extends PurchaseDecorator{
    
    public LoyaltyDiscount(Purchase purchase) {
        super(purchase);
    }

    public double calculatePrice()
    {
        return purchase.calculatePrice()*.9;
    }
    
}

class SeasonalDiscount extends PurchaseDecorator{
    
    public SeasonalDiscount(Purchase purchase) {
        super(purchase);
    }

    public double calculatePrice()
    {
        return purchase.calculatePrice()-100;
    }
    
}

class HighValueDiscount extends PurchaseDecorator{
    
    public HighValueDiscount(Purchase purchase) {
        super(purchase);
    }

    public double calculatePrice()
    {
        return purchase.calculatePrice()*.98;
    }
    
}

class Main{
    public static void main(String[] args) {
        double  price = 12000;

        boolean isPremium = true;
        boolean isSeason = true;

        Purchase purchase = new BasePurchase(price);
        Purchase discounted = purchase;

        if(isPremium)
        {
            discounted = new LoyaltyDiscount(discounted);
        }
        if(isSeason)
        {
            discounted = new SeasonalDiscount(discounted);
        }
        if(price>10000)
        {
            discounted = new HighValueDiscount(discounted);
        }

        System.out.println(discounted.calculatePrice());
    }
}