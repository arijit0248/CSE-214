interface Transport {
    String deliver();
}

class BikeTransport implements Transport {
    public String deliver() {
        return "Delivered using bike courier";
    }
}

class VanTransport implements Transport {
    public String deliver() {
        return "Delivered using van";
    }
}

class DroneTransport implements Transport {
    public String deliver() {
        return "Delivered using drone";
    }
}

class RobotTransport implements Transport {
    public String deliver() {
        return "Delivered using robot";
    }
}

abstract class Delivery {
    protected Transport transport;

    public Delivery(Transport transport) {
        this.transport = transport;
    }

    abstract double calculateCost();
    abstract String estimateTime();

    public void processDelivery() {
        System.out.println("Cost: " + calculateCost());
        System.out.println("Time: " + estimateTime());
        System.out.println(transport.deliver());
    }
}


class StandardDelivery extends Delivery {
    public StandardDelivery(Transport transport) {
        super(transport);
    }

    double calculateCost() {
        return 50;
    }

    String estimateTime() {
        return "Within 24 hours";
    }
}


class ExpressDelivery extends Delivery {
    public ExpressDelivery(Transport transport) {
        super(transport);
    }

    double calculateCost() {
        return 100;
    }

    String estimateTime() {
        return "Within 4 hours";
    }
}


class ScheduledDelivery extends Delivery {
    public ScheduledDelivery(Transport transport) {
        super(transport);
    }

    double calculateCost() {
        return 70;
    }

    String estimateTime() {
        return "Delivered at chosen time slot";
    }
}

public class Main {
    public static void main(String[] args) {
        Delivery d1 = new StandardDelivery(new BikeTransport());
        d1.processDelivery();

        Delivery d2 = new ExpressDelivery(new DroneTransport());
        d2.processDelivery();

        Delivery d3 = new ScheduledDelivery(new RobotTransport());
        d3.processDelivery();
    }
}





