
import java.util.ArrayList;
import java.util.List;

public class Cart implements Sellable{
    private String name;
    private List<Sellable> items = new ArrayList<>();

    public Cart(String name)
    {
        this.name = name;
    }

    public void add(Sellable s)
    {
        items.add(s);
    }
    public List<Sellable> getItems()
    {
        return items;
    }

    @Override
    public String getName() {
        return "Cart: "+name;
    }

    @Override
    public double getPrice() {
        double price = 0.0;
        for(Sellable s:items)
        {
            price += s.getPrice();
        }
        return price;
    }

    @Override
    public double getDuration() {
        double hours = 0.0;
        for(Sellable s:items)
        {
            hours += s.getDuration();
        }
        return hours;
    }

    @Override
    public void print(String indent) {
        System.out.printf("%s+ %s | %.2f hours | $%.2f%n",indent,getName(),getDuration(),getPrice());
        for(Sellable s:items)
        {
            s.print(indent+" ");
        }
    }

    @Override
    public int countModules() {
        int count = 0;
        for(Sellable s:items)
        {
            count += s.countModules();
        }
        return count;
    }
    
}
