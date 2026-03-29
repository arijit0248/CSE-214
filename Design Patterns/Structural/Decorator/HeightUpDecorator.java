public class HeightUpDecorator extends Decorator{
    public HeightUpDecorator(Character character)
    {
        super(character);
    }
    @Override
    public void getAbilities() {
        character.getAbilities();
        System.out.println(" with extra height");
    }

}