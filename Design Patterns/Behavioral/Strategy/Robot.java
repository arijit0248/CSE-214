public class Robot{
    private Talkable t;
    private Walkable w;
    private Flyable f;

    public Robot(Talkable t,Walkable w,Flyable f) {
        this.t = t;
        this.w = w;
        this.f = f;
    }
    public void talk()
    {
        t.talk();
    }
    public void fly()
    {
        f.fly();
    }
    public void walk()
    {
        w.walk();
    }
}