public abstract class CoffeeDecorator implements Coffee{
    protected Coffee decoratedCoffee;
    public CoffeeDecorator(Coffee decoratedCoffee)
    {
        this.decoratedCoffee = decoratedCoffee;
    }

@Override
    public String getIngredients()
    {
        return decoratedCoffee.getIngredients();
    }

    @Override
    public int getCost()
    {
        return decoratedCoffee.getCost();
    }
}
