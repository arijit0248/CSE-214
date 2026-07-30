public class DevelopingCountryDiscount extends SellableDecorator{
    private static final double OFF = 10.0;
    public DevelopingCountryDiscount(Sellable base)
    {
        super(base);
    }
    @Override
    public double getPrice()
    {
        return clampNonNegative(base.getPrice() - OFF);
    }
    @Override
    public void print(String indent)
    {
        base.print(indent);
        System.out.printf("%s -- Discount: Develeoping Country Discount     -%.2f%n",indent,OFF);
        System.out.printf("%s = Subtotal after discount:                    $%.2f%n",indent,getPrice());
    }
    
}
