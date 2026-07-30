public class NoCoinState implements VendingMachineState{

    

    @Override
    public VendingMachineState insertCoin(VendingMachine machine, int amount) {
        // TODO Auto-generated method stub
        machine.setInsertedCoins(amount);
        System.out.println("Coin inserted.Current balance is: "+amount);
        return machine.getHasCoinState();
    }

    @Override
    public VendingMachineState selectItem(VendingMachine machine) {
        System.out.println("Insert coin First ");
        return machine.getNoCoinState();
    }

    @Override
    public VendingMachineState dispense(VendingMachine machine) {
        System.out.println("Insert coin First ");
        return machine.getNoCoinState();
    }

    @Override
    public VendingMachineState returnCoin(VendingMachine machine) {
        System.out.println("Insert coin First ");
        return machine.getNoCoinState();
    }

    @Override
    public VendingMachineState refill(VendingMachine machine, int quantity) {
        machine.incrementItemCount(quantity);
        return machine.getNoCoinState();
    }
    @Override
    public String getStateName()
    {
        return "No Coin";
    }

}