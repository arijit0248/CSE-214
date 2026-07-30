public class FanCommand implements Command {
    private Fan fan;

    public FanCommand(Fan fan) {
        this.fan = fan;
    }

    @Override
    public void on() {
        fan.on();
    }

    @Override
    public void off() {
        fan.off();
    }

}