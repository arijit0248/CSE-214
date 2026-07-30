
import java.util.Scanner;

public class CoffeeTong {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();
        while (true) {
        System.out.println("Select coffee type (1: Americano, 2: Espresso, 3: Cappuccino, 4:Mocha, 0: Finish): ");
        int choice = scanner.nextInt();
        if (choice == 0) break;
        Coffee coffee;
            switch (choice) {
            case 1:
                coffee = new Americano(new BasicBlackCoffee());
                break;
            case 3:
                coffee = new Cappuccino(new BasicMilkCoffee());
                break;
            default:
                System.out.println("Invalid choice.");
                continue;
            }
            order.addCoffee(coffee);
        }
        order.printOrderDetails();
    }
}
