public class Client {
    public static void main(String[] args) {
        Character character = new StarpowerDecorator(new FlyDecorator(new HeightUpDecorator(new Mario())));
        character.getAbilities();
    }
}
