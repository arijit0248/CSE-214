public class FlyDecorator extends Decorator{
    public FlyDecorator(Character character)
    {
        super(character);
    }
    @Override
    public void getAbilities() {
        character.getAbilities();
        System.out.println(" with flying ability");
    }

}