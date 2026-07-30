public class SoldOutState implements VendingMachineState{

    @Override
    public void insertCoin(VendingMachine machine, int amount) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void selectItem(VendingMachine machine) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void dispense(VendingMachine machine) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void returnCoin(VendingMachine machine) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void refill(VendingMachine machine, int quantity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getStateName()
    {
        return "Items Sold out";
    }

}