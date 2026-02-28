public class Client {
    public static void main(String[] args) {
        Remote remote = new Remote(2);
        remote.setCommand(0, new LightCommand(new Light()));
        remote.setCommand(1, new FanCommand(new Fan()));
        remote.press(0);
        remote.press(0);
        remote.press(1);
    }
}
