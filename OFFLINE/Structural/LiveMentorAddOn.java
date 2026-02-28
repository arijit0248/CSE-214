public class LiveMentorAddOn extends SellableDecorator{
    private static final double FEE = 20.0;
    public LiveMentorAddOn(Sellable base)
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
        System.out.printf("%s -- Add-on: Live Mentor Support       +%.2f%n",indent,FEE);
        System.out.printf("%s = Subtotal after add-on:             $%.2f%n",indent,getPrice());
    }
    
}
