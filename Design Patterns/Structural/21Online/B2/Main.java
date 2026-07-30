// ─────────────────────────────────────────────
// GIVEN CODE (do not modify)
// ─────────────────────────────────────────────

interface Component {
    double getPrice();
    String getDescription();
}

class HardwareComponent implements Component {
    private String name;
    private double price;

    public HardwareComponent(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public double getPrice() { return price; }

    @Override
    public String getDescription() { return name; }
}

// ─────────────────────────────────────────────
// ABSTRACT DECORATOR
// ─────────────────────────────────────────────

abstract class FeatureDecorator implements Component {
    protected Component component;

    public FeatureDecorator(Component component) {
        this.component = component;
    }

    @Override
    public double getPrice() { return component.getPrice(); }

    @Override
    public String getDescription() { return component.getDescription(); }
}

// ─────────────────────────────────────────────
// CONCRETE DECORATORS
// ─────────────────────────────────────────────

class ExtendedWarranty extends FeatureDecorator {
    public ExtendedWarranty(Component component) { super(component); }

    @Override
    public double getPrice() { return component.getPrice() + 50; }

    @Override
    public String getDescription() { return component.getDescription() + " + Extended Warranty"; }
}

class InstallationService extends FeatureDecorator {
    public InstallationService(Component component) { super(component); }

    @Override
    public double getPrice() { return component.getPrice() + 30; }

    @Override
    public String getDescription() { return component.getDescription() + " + Installation Service"; }
}

class PerformanceBoost extends FeatureDecorator {
    public PerformanceBoost(Component component) { super(component); }

    @Override
    public double getPrice() { return component.getPrice() + 100; }

    @Override
    public String getDescription() { return component.getDescription() + " + Performance Boost"; }
}

// ─────────────────────────────────────────────
// DRIVER
// ─────────────────────────────────────────────

public class Main {
    public static void main(String[] args) {

        // Base component
        Component cpu = new HardwareComponent("Intel i9 CPU", 450);
        System.out.println(cpu.getDescription() + " | $" + cpu.getPrice());

        // Add one feature
        cpu = new ExtendedWarranty(cpu);
        System.out.println(cpu.getDescription() + " | $" + cpu.getPrice());

        // Add another feature on top
        cpu = new PerformanceBoost(cpu);
        System.out.println(cpu.getDescription() + " | $" + cpu.getPrice());

        // Add all three features
        Component gpu = new HardwareComponent("RTX 4080 GPU", 900);
        gpu = new ExtendedWarranty(gpu);
        gpu = new InstallationService(gpu);
        gpu = new PerformanceBoost(gpu);
        System.out.println(gpu.getDescription() + " | $" + gpu.getPrice());
    }
}
