public interface VendingMachineState{
    VendingMachineState insertCoin(VendingMachine machine,int amount);
    VendingMachineState selectItem(VendingMachine machine);
    VendingMachineState dispense(VendingMachine machine);
    VendingMachineState returnCoin(VendingMachine machine);
    VendingMachineState refill(VendingMachine machine,int quantity);
    String getStateName();
}