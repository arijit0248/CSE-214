public class Client {
    public static void main(String[] args) {
        Car car1 = new Sedan(new PetrolEngine());
        car1.drive();
        Car car2 = new SUV(new GasEngine());
        car2.drive();
    }
}
