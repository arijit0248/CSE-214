public class SpecialDurationDiscount extends SellableDecorator{
    private static final double OFF = 12.0;
    private static final double THRESHOLD = 5.0;
    public SpecialDurationDiscount(Sellable base)
    {
        super(base);
    }
    private boolean qualifies()
    {
        if(base.getDuration() >= THRESHOLD)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    public double getPrice()
    {
        if(qualifies())
        {
            return clampNonNegative(base.getPrice() - OFF);
        }
        else
        {
            return base.getPrice();
        }
    }
    @Override
    public void print(String indent)
    {
        base.print(indent);
        if(qualifies())
        {
            System.out.printf("%s -- Discount: Special Duration Discount for 5+ hours    -%.2f%n",indent,OFF);
            System.out.printf("%s = Subtotal after discount:                             $%.2f%n",indent,getPrice());
        }
        else
        {
            System.out.printf("%s -- Discount: Special Duration Discount not applied as (%.2f hours < %.2f hours required)%n",indent,base.getPrice(),THRESHOLD);
            System.out.printf("%s = Subtotal unchanged:                                 $%.2f%n",indent,getPrice());
        }
    }
    
}
