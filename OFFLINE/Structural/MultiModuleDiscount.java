public class MultiModuleDiscount extends SellableDecorator{
    private static final double OFF = 15.0;
    private static final int MIN_MODULES = 2;
    public MultiModuleDiscount(Sellable base)
    {
        super(base);
    }
    private boolean qualifies()
    {
        if(base.countModules() >= MIN_MODULES)
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
            System.out.printf("%s -- Discount: Multi Modules %d+modules    -%.2f%n",indent,MIN_MODULES,OFF);
            System.out.printf("%s = Total after all discount:              $%.2f%n",indent,getPrice());
        }
        else
        {
            System.out.printf("%s -- Discount: Special Duration Discount not applied as (only %d module, %d required)%n",indent,base.countModules(),MIN_MODULES);
            System.out.printf("%s = Total unchanged:                                    $%.2f%n",indent,getPrice());
        }
    }
    
}
