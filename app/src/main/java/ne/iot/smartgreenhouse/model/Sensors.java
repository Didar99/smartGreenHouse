package ne.iot.smartgreenhouse.model;

public class Sensors {

    public int id;
    public String command;
    public int state;

    public Sensors() {

    }

    public Sensors(int id, String command, int state) {
        this.id = id;
        this.command = command;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}