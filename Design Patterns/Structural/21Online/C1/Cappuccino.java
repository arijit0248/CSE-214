public class Cappuccino extends CoffeeDecorator{
    
    public Cappuccino(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public String getIngredients()
    {
        return decoratedCoffee.getIngredients()+"cinnamon powder";
    }

    @Override
    public int getCost()
    {
        return decoratedCoffee.getCost()+50;
    }
}
