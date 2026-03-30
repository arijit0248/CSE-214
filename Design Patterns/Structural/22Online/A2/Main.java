
import java.util.ArrayList;
import java.util.List;

interface BazarComponent{
    String getName();
    int getPrice();
    int getWeight();
    void showDetails();
}

class SingleItem implements BazarComponent{
    private String name;
    private int weight;
    private int price;

    public SingleItem(String name,int weight,int price)
    {
        this.name = name;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public void showDetails() {
        System.out.println("- "+name+"(Price: "+price+" Weight "+weight+")");
    }

}

class Package implements BazarComponent{
    private String name;
    private List<BazarComponent> children = new ArrayList<>();

    public Package(String name)
    {
        this.name = name;
    }

    public void addItem(BazarComponent c)
    {
        children.add(c);
    }

    public void removeItem(BazarComponent c)
    {
        children.remove(c);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        int total = 0;
        for(BazarComponent bc:children)
        {
            total += bc.getPrice();
        }
        return total;
    }

    @Override
    public int getWeight() {
        int total = 0;
        for(BazarComponent bc:children)
        {
            total += bc.getWeight();
        }
        return total;
    }

    @Override
    public void showDetails() {
        System.out.println(name);
        for(BazarComponent bc:children)
        {
            bc.showDetails();
        }
    }

}

class Main{
    public static void main(String[] args) {
        Package small = new Package("small");
        SingleItem rice = new SingleItem("rice", 10,100);
        SingleItem oil = new SingleItem("oil", 5, 120);
        small.addItem(oil);
        small.addItem(rice);
        small.showDetails();
    }
}