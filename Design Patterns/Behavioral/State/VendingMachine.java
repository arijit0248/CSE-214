public class VendingMachine {
    private VendingMachineState state;
    private int quantity;
    private int price;
    private int insertedCoins;
    private VendingMachineState noCoinState;
    private VendingMachineState hasCoinState;
    private VendingMachineState dispenseState;
    private VendingMachineState soldOutState;

    public VendingMachine(int quantity,int price)
    {
        this.quantity = quantity;
        this.price = price;

        if(quantity>0)
        {
            state = noCoinState;
        }
        else
        {
            state = soldOutState;
        }

        noCoinState = new NoCoinState();
        hasCoinState = new HasCoinState();
        dispenseState = new DispenseState();
        soldOutState = new SoldOutState();
    }

    public VendingMachineState getNoCoinState() {
        return noCoinState;
    }

    public VendingMachineState getHasCoinState() {
        return hasCoinState;
    }

    public VendingMachineState getDispenseState() {
        return dispenseState;
    }

    public VendingMachineState getSoldOutState() {
        return soldOutState;
    }

    public void insertCoin(int amount)
    {
        state = state.insertCoin(this,amount);
    }
    public void dispense()
    {
        state = state.dispense(this);
    }
    public void selectItem()
    {
        state = state.selectItem(this);
    }
    public void refill(int quantity)
    {
        state = state.refill(this,quantity);
    }

    public void setInsertedCoins(int insertedCoins) {
        this.insertedCoins = insertedCoins;
    }

    public void incrementItemCount(int q)
    {
        quantity = q;
    }
    public void addCoin(int amount)
    {
        insertedCoins += amount;
    }
}
