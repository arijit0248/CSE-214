public class Checkout {
    public void checkout(Sellable item)
    {
        System.out.println("******** CHECKOUT - EDULEARN ********");
        item.print("");
        System.out.println("-------------------------------------");
        System.out.printf("TOTAL DURATION : %.2f hours%n",item.getDuration());
        System.out.printf("TOTAL PRICE    : $%.2f%n",item.getPrice());
        System.out.println("-------------------------------------");
    }
}
