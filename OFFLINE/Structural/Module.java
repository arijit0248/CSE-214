public class Module extends CompositeContent implements Sellable{
    private String name;
    
    public Module(String name)
    {
        super("Module: "+name);
        this.name = name;
    }

    @Override
    public double getPrice() {
        double price = 0.0;
        for(Content c:getChildren())
        {
            if(c instanceof Sellable s)
            {
                price += s.getPrice();
            }
        }
        return price;
    }

    @Override
    public int countModules() {
        return 1;
    }

    @Override
    public void print(String indent)
    {
        System.out.printf("%s+ Module : %s | %.2f hours | $%.2f%n",indent,name,getDuration(),getPrice());
        for(Content c:getChildren())
        {
            if(c instanceof Sellable s)
            {
                s.print(indent+" ");
            }
            else
            {
                c.print(indent+" ");
            }
        }
    }
}
