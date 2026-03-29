public class StarpowerDecorator extends Decorator{
    public StarpowerDecorator(Character character)
    {
        super(character);
    }
    @Override
    public void getAbilities() {
        character.getAbilities();
        System.out.println(" with star power");
    }

}