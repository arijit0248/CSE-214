import java.util.ArrayList;
import java.util.List;

public abstract class CompositeContent implements Content{
    private String name;
    private List<Content> children = new ArrayList<>();

    public CompositeContent(String name)
    {
        this.name = name;
    }

    public void add(Content content)
    {
        children.add(content);
    }
    public List<Content> getChildren()
    {
        return children;
    }
    @Override
    public String getName()
    {
        return name;
    }
    @Override
    public double getDuration()
    {
        double hours = 0.0;
        for(Content c:children)
        {
            hours += c.getDuration();
        }
        return hours;
    }
    @Override
    public void print(String indent)
    {
        System.out.printf("%s+ %s | %.2f hours%n",indent,name,getDuration());
        for(Content c:children)
        {
            c.print(indent+" ");
        }
    }  
}
