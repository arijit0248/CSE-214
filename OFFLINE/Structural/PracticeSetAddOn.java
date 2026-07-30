public class PracticeSetAddOn extends SellableDecorator{
    private static final double FEE = 10.0;
    public PracticeSetAddOn(Sellable base)
    {
        super(base);
    }
    @Override
    public double getPrice()
    {
        return base.getPrice() + FEE;
    }
    @Override
    public void print(String indent)
    {
        base.print(indent);
        System.out.printf("%s -- Add-on: Practice Question Set     +%.2f%n",indent,FEE);
        System.out.printf("%s = Subtotal after add-on:             $%.2f%n",indent,getPrice());
    }
    
}
