public class Lesson implements Content{
    private String name;
    private double duration;

    public Lesson(String name,double duration)
    {
        this.name = name;
        this.duration = duration;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getDuration() {
        return duration;
    }

    @Override
    public void print(String indent) {
        System.out.printf("%s- Lesson: %s | %.2f hours%n",indent,name,duration);
    }
    
}
