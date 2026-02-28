
import java.util.Vector;

public class Channel implements IChannel{
    private String name;
    private String latestVideo;
    private Vector<ISubscriber> subscribers;

    public Channel(String name)
    {
        this.name = name;
        this.subscribers = new Vector<>();
        this.latestVideo = null;
    }

    public Channel(String latestVideo, String name) {
        this.latestVideo = latestVideo;
        this.name = name;
    }
    @Override
    public void subscribe(ISubscriber s) {
        subscribers.add(s);
    }

    @Override
    public void unsubscribe(ISubscriber s) {
       subscribers.remove(s);
    }

    @Override
    public void notifySub() {
        for(ISubscriber subscriber:subscribers)
        {
            subscriber.update();
        }
    }

    public void uploadVideo(String title)
    {
        latestVideo = title;
        notifySub();
    }

    public String getLatestVideo() {
        return latestVideo;
    }

    public String getName() {
        return name;
    }

}