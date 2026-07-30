public class Client {
    public static void main(String[] args) {
        Channel channel = new Channel("MrBeast");
        channel.subscribe(new Subscriber("Arijit",channel));
        channel.subscribe(new Subscriber("Srijon",channel));
        channel.subscribe(new Subscriber("Surjo",channel));
        channel.uploadVideo("50 men vs 50 women");
    }
}
