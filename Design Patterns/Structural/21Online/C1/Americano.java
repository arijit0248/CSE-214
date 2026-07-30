public class Americano extends CoffeeDecorator{
    
    public Americano(Coffee decoratedCoffee) {
        super(decoratedCoffee);
    }

    @Override
    public String getIngredients()
    {
        return decoratedCoffee.getIngredients()+"grinded coffee beans";
    }

    @Override
    public int getCost()
    {
        return decoratedCoffee.getCost()+30;
    }
}
