public class Course extends CompositeContent implements Sellable{
    private String name;
    private double price;

    public Course(String name,double price)
    {
        super("Course: "+name);
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public int countModules() {
        return 0;
    }
    @Override
    public void print(String indent)
    {
        System.out.printf("%s+ Course: %s | %.2f hours | $%.2f%n",indent,name,getDuration(),getPrice());
        for(Content content:getChildren())
        {
            content.print(indent+" ");
        }
    }

}
