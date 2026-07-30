public class BasicBlackCoffee implements Coffee{

    @Override
    public String getIngredients() {
        return "water+Cofee beans";
    }

    @Override
    public int getCost() {
        return 130;
    }
    
}
