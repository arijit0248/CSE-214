interface SmartDevice{
    void turnOn();
    void turnOff();
}

class Device{
    private SmartDevice device;
    public Device(SmartDevice device)
    {
        this.device = device;
    }
    public void work()
    {
        device.turnOn();
        device.turnOff();
    }
}

class OldSmartBulb{
    public void powerOn()
    {
        System.out.println("Bulb turning on");
    }
    public void powerOff()
    {
        System.out.println("Bulb turning off");
    }
}

class LegacyHeater{
    public void startHeating()
    {
        System.out.println("Heating start");
    }
    public void stopHeating()
    {
        System.out.println("heating off");
    }

}

class BulbAdapter implements SmartDevice{
    private OldSmartBulb bulb;

    public BulbAdapter(OldSmartBulb bulb)
    {
        this.bulb = bulb;
    }

    @Override
    public void turnOn() {
        bulb.powerOn();
    }

    @Override
    public void turnOff() {
        bulb.powerOff();
    }
    
}

class HeaterAdapter implements SmartDevice{
    private LegacyHeater heater;

    public HeaterAdapter(LegacyHeater heater)
    {
        this.heater = heater;
    }

    @Override
    public void turnOn() {
        heater.startHeating();
    }

    @Override
    public void turnOff() {
        heater.stopHeating();
    }
    
}


class Main{
    public static void main(String[] args) {
        Device  device1 = new Device(new BulbAdapter(new OldSmartBulb()));
        device1.work();
        Device device2 = new Device(new HeaterAdapter(new LegacyHeater()));
        device2.work();
    }
}