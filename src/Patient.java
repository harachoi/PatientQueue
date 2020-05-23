/**
 * A class representing a patient.
 *
 * @author TODO
 */
public class Patient {
    // instance variables
    String name;
    int arrival_time;
    int urgency;

    // constructor
    public Patient(String name, int arrival_time, int urgency) {
        this.name = name;
        this.arrival_time = arrival_time;
        this.urgency = urgency;
    }

    // return this patient's arrival time
    public int arrival_time() {
        return arrival_time;
    }

    // return this patient's urgency
    public int urgency() {
        return urgency;
    }

    // return this patient's wait time
    public int wait_time(int time) {
        return time - arrival_time;
    }
}
