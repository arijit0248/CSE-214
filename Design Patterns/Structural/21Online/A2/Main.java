import java.util.ArrayList;
import java.util.List;

// Step 1: Component interface — uniform treatment for both leaf and composite
interface HardwareComponent {
    String getName();
    double getPrice();
    void display(String indent);
}

// Step 2: Leaf — individual hardware component (no children)
class HardwarePart implements HardwareComponent {
    private String name;
    private double price;

    public HardwarePart(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() { return price; }

    @Override
    public void display(String indent) {
        System.out.println(indent + "- " + name + ": $" + price);
    }
}

// Step 3: Composite — a bundle that holds components or other bundles
class Bundle implements HardwareComponent {
    private String name;
    private List<HardwareComponent> components = new ArrayList<>();

    public Bundle(String name) {
        this.name = name;
    }

    public void addComponent(HardwareComponent component) {
        components.add(component);
        System.out.println("  [+] Added \"" + component.getName() + "\" to \"" + name + "\"");
    }

    public void removeComponent(HardwareComponent component) {
        boolean removed = components.remove(component);
        if (removed) {
            System.out.println("  [-] Removed \"" + component.getName() + "\" from \"" + name + "\"");
        } else {
            System.out.println("  [!] \"" + component.getName() + "\" not found in \"" + name + "\"");
        }
    }

    @Override
    public String getName() { return name; }

    @Override
    public double getPrice() {
        double total = 0;
        for (HardwareComponent c : components) {
            total += c.getPrice(); // Recursively sums — works for nested bundles too
        }
        return total;
    }

    @Override
    public void display(String indent) {
        System.out.println(indent + "[Bundle] " + name + " — Total: $" + getPrice());
        for (HardwareComponent c : components) {
            c.display(indent + "    "); // Recurse into children
        }
    }
}

// Step 4: Driver
public class Main {
    public static void main(String[] args) {

        // --- Individual components ---
        HardwareComponent cpu       = new HardwarePart("Intel Core i9 CPU",   450.00);
        HardwareComponent ram       = new HardwarePart("32GB DDR5 RAM",        120.00);
        HardwareComponent storage   = new HardwarePart("1TB NVMe SSD",         100.00);
        HardwareComponent gpu       = new HardwarePart("NVIDIA RTX 4080",      900.00);
        HardwareComponent extraGpu  = new HardwarePart("NVIDIA RTX 4090",     1200.00);
        HardwareComponent monitor   = new HardwarePart("4K Gaming Monitor",    350.00);
        HardwareComponent workCpu   = new HardwarePart("AMD Threadripper CPU", 700.00);
        HardwareComponent ecc       = new HardwarePart("128GB ECC RAM",        400.00);
        HardwareComponent nas       = new HardwarePart("4TB NAS Storage",      250.00);

        System.out.println("========================================");
        System.out.println("   COMPUTER COMPONENT STORE SYSTEM");
        System.out.println("========================================\n");

        // --- Individual purchase ---
        System.out.println(">> Single component purchase:");
        cpu.display("  ");
        System.out.println();

        // --- Basic Gaming Setup (sub-bundle) ---
        System.out.println(">> Building Basic Gaming Setup:");
        Bundle basicGaming = new Bundle("Basic Gaming Setup");
        basicGaming.addComponent(cpu);
        basicGaming.addComponent(ram);
        basicGaming.addComponent(storage);
        basicGaming.addComponent(gpu);
        System.out.println();
        basicGaming.display("  ");
        System.out.println();

        // --- Ultimate Gaming Setup (bundle containing another bundle) ---
        System.out.println(">> Building Ultimate Gaming Setup:");
        Bundle ultimateGaming = new Bundle("Ultimate Gaming Setup");
        ultimateGaming.addComponent(basicGaming);  // Nested bundle!
        ultimateGaming.addComponent(extraGpu);
        ultimateGaming.addComponent(monitor);
        System.out.println();
        ultimateGaming.display("  ");
        System.out.println();

        // --- Workstation Setup ---
        System.out.println(">> Building Workstation Setup:");
        Bundle workstation = new Bundle("Workstation Setup");
        workstation.addComponent(workCpu);
        workstation.addComponent(ecc);
        workstation.addComponent(nas);
        workstation.addComponent(storage);
        System.out.println();
        workstation.display("  ");
        System.out.println();

        // --- Removing a component ---
        System.out.println(">> Customer removes NAS Storage from Workstation:");
        workstation.removeComponent(nas);
        System.out.println();
        workstation.display("  ");
        System.out.println();

        // --- Trying to remove something not in the bundle ---
        System.out.println(">> Attempting to remove GPU from Workstation (not present):");
        workstation.removeComponent(gpu);
        System.out.println();

        // --- Price summary ---
        System.out.println("========================================");
        System.out.println("         PRICE SUMMARY");
        System.out.println("========================================");
        System.out.printf("  %-30s $%.2f%n", cpu.getName(),              cpu.getPrice());
        System.out.printf("  %-30s $%.2f%n", basicGaming.getName(),      basicGaming.getPrice());
        System.out.printf("  %-30s $%.2f%n", ultimateGaming.getName(),   ultimateGaming.getPrice());
        System.out.printf("  %-30s $%.2f%n", workstation.getName(),      workstation.getPrice());
    }
}
