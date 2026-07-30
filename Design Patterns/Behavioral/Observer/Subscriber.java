

public class Subscriber implements ISubscriber{
    private String name;
    private Channel channel;
    
    public Subscriber(String name,Channel channel)
    {
        this.name = name;
        this.channel = channel;
    }
    @Override
    public void update() {
        System.out.println("Hey "+name+" "+channel.getName()+" just uploaded a new video "+channel.getLatestVideo());
    }
}