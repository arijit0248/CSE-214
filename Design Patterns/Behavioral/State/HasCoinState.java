public class HasCoinState implements VendingMachineState{

    @Override
    public VendingMachineState insertCoin(VendingMachine machine, int amount) {
        machine.addCoin(amount);

        return machine.getHasCoinState();
    }

    @Override
    public VendingMachineState selectItem(VendingMachine machine) {
        return machine.getDispenseState();
    }

    @Override
    public VendingMachineState dispense(VendingMachine machine) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public VendingMachineState returnCoin(VendingMachine machine) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public VendingMachineState refill(VendingMachine machine, int quantity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getStateName()
    {
        return "Has coin";
    }

}