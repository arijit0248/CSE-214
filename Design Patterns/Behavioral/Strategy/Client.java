public class Client {
    public static void main(String[] args) {
        Robot robot = new Robot(new NoTalk(),new NormalWalk(),new FlyWithJetpack());
        robot.talk();
        robot.walk();
        robot.fly();
    }
    

}
