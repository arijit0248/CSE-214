import java.util.Vector;

public class Remote {
    private Command[] buttons;
    private Boolean[] isPressed;

    public Remote(int number) {
        buttons = new Command[number];
        isPressed = new Boolean[number];
        for (int i = 0; i < number; i++) {
            buttons[i] = null;
            isPressed[i] = false;
        }
    }

    public void setCommand(int index, Command command) {
        buttons[index] = command;
    }

    public void press(int index) {
        if (!isPressed[index]) {
            buttons[index].on();
        } else {
            buttons[index].off();
        }
        isPressed[index] = !isPressed[index];
    }

}
