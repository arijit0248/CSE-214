

public class Main {
    public static void main(String[] args) {
        Lesson l1 = new Lesson("Creational Design Pattern", 1.0);
        Lesson l2 = new Lesson("Behavioral Design Pattern", 2.0);
        Lesson l3 = new Lesson("Structural Design Pattern", 2.5);
        Lesson l4 = new Lesson("Professional Ethics", .5);
        Lesson l5 = new Lesson("Load Testing", 1.5);
        Course c1 = new Course("Design Pattern", 20.0);
        c1.add(l1);
        c1.add(l2);
        c1.add(l3);
        Course c2 = new Course("Ethics", 10.0);
        c2.add(l4);
        Course c3 = new Course("Testing", 15.0);
        c3.add(l5);

        Module m1 = new Module("Software Engineering Part-1");
        m1.add(c1);
        m1.add(c2);
        Module m2 = new Module("Software Engineering Part-2");
        m2.add(c3);

        Cart cart = new Cart("Sttudent Purchase");
        cart.add(m1);
        cart.add(m2);

        Sellable finalPurchase = new MultiModuleDiscount(
                                    new SpecialDurationDiscount(
                                        new DevelopingCountryDiscount(
                                            new PracticeSetAddOn(
                                                new LiveMentorAddOn(cart)))));
        
        System.out.println("Scenario 1 : Full Cart - add ons + all discounts");
        new Checkout().checkout(finalPurchase);

        System.out.println("Scenario 2 : Single Course Purchase");
        new Checkout().checkout(c1);

        Sellable shortPurchase = new SpecialDurationDiscount(
                                    new DevelopingCountryDiscount(c1));
        System.out.println("Scenario 3 : 1 course with developing country discount and special duration discount");
        new Checkout().checkout(shortPurchase);
    }
}
