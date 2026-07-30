public class BasicMilkCoffee implements Coffee{

    @Override
    public String getIngredients() {
        return "milk+Cofee beans";
    }

    @Override
    public int getCost() {
        return 180;
    }
    
}
