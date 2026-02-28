public abstract class SellableDecorator implements Sellable{
    protected Sellable base;
    protected SellableDecorator(Sellable base)
    {
        this.base = base;
    }
    @Override
    public String getName()
    {
        return base.getName();
    }
    @Override
    public double getPrice()
    {
        return base.getPrice();
    }
    @Override
    public double getDuration()
    {
        return base.getDuration();
    }
    @Override
    public int countModules()
    {
        return base.countModules();
    }
    @Override
    public void print(String indent)
    {
        base.print(indent);
    }
    protected double clampNonNegative(double v)
    {
        return Math.max(0.0, v);
    }
}
