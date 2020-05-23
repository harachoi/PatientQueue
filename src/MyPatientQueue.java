import java.util.LinkedList;

/**
 * A Patient queue implementation using a dynamically-sized circular array.
 */
public class MyPatientQueue {

    private LinkedList<Patient> patientList;
    private int head;
    private int tail;
    private int size;
    private int tempTail;

    public MyPatientQueue() {
        patientList = new LinkedList<Patient>();

        head = 0;
        tail = 0;
        tempTail = 0;
        size = 0;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    // return the number of patients in the queue
    public int size() {
        return size;
    }

    // add patient to end of queue
    // resize array, if needed
    public void enqueue(Patient p) {
        LinkedList<Patient> store = new LinkedList<Patient>();
        int tempSize;
        int count = 0;

        if (patientList.isEmpty()) {
            patientList.addLast(p);
            head = 0;
            tail = 0;
            tempTail = 0;
        } else {
            patientList.set(tempTail, p);
        }
        size++;
        tail++;
        tempTail++;
        tempSize = patientList.size();


        while (count != patientList.size()) {
            if (patientList.get(count) != null) {
                store.addLast(patientList.get(count));
            }
            count++;
        }
        if (size == patientList.size()) {
            patientList = new LinkedList<Patient>();
            for (int j = 0; j < tempSize * 2; j++) {
                if (j < store.size()) {
                    patientList.addLast(store.get(j));
                } else {
                    patientList.addLast(null);
                }
            }
        }

        //if patient number is less than the 'patientList'/4 then resize the array in half.
        if (patientList.size() / 4 >= 7 && patientList.size() / 4 >= size) {
            patientList = new LinkedList<Patient>();
            for (int j = 0; j < tempSize / 2; j++) {
                if (j < store.size()) {
                    if (store.get(j) == null) {
                        patientList.add(j, null);
                    } else {
                        patientList.addLast(store.get(j));
                    }
                } else if (j >= store.size()) {
                    patientList.addLast(null);
                }
            }
        } else if (size != tempSize) {
            patientList = new LinkedList<Patient>();
            for (int i = 0; i < tempSize; i++) {
                if (i < store.size()) {
                    patientList.addLast(store.get(i));
                } else if (i >= store.size()) {
                    patientList.addLast(null);
                }
            }
        }
    }

    // remove and return the patient at the head of the queue
    // resize array, if needed
    public Patient dequeue() {
        LinkedList<Patient> store = new LinkedList<Patient>();

        int tempSize;
        Patient result;

        if (patientList.isEmpty()) {
            return null;
        } else {
            result = patientList.set(0, null);
            head++;
            size--;
            tempTail--;
            tempSize = patientList.size();
        }
        if (tempTail == -1) {
            tempTail = 0;
            size = 0;
        }
        for (int i = 1; i < patientList.size(); i++) {
            store.addLast(patientList.get(i));
        }
        if (patientList.size() / 4 >= 7 && tempSize / 4 >= size) {
            patientList = new LinkedList<Patient>();
            for (int j = 0; j < tempSize; j++) {
                if (j < tempSize / 2) {
                    if (store.get(j) == null) {
                        patientList.add(j, null);
                    } else {
                        patientList.addLast(store.get(j));
                    }
                } else if (j >= tempSize / 2) {
                    patientList.addLast(null);
                }
            }
        } else {
            patientList = new LinkedList<>();
            for (int j = 0; j < tempSize; j++) {
                if (j < store.size()) {
                    patientList.addLast(store.get(j));
                } else if (j >= store.size()) {
                    patientList.add(j, null);
                }
            }
        }
        return result;
    }

    // return, but do not remove, the patient at index i
    public Patient get(int i) {
        return patientList.get(i);
    }

    // add Patient p to front of queue
    // resize array, if needed
    public void push(Patient p) {

        int temp;

        if (patientList.isEmpty()) {
            patientList.addFirst(p);
            head = 0;
        } else {
            patientList.addFirst(p);
            patientList.removeLast();
        }
        size++;
        tail++;
        tempTail++;
        temp = patientList.size();

        if (size == patientList.size()) {
            for (int j = 0; j < temp * 2; j++) {
                if (j > temp / 2) {
                    patientList.addLast(null);
                }
            }
        }
    }

    // remove and return Patient at index i from queue
    // shift patients down to fill hole left by removed patient
    // resize array, if needed
    public Patient dequeue(int i) {
        LinkedList<Patient> store = new LinkedList<Patient>();

        if (i < 0 || i >= tail) {
            return null;
        }

        Patient temp;
        int tempSize = patientList.size();
        temp = patientList.get(i);
        patientList.set(i, null);
        size--;
        tail--;
        tempTail--;

        for (int k = 0; k < patientList.size(); k++) {
            if (patientList.get(k) != null) {
                store.addLast(patientList.get(k));
            }
        }
        if (patientList.size() / 4 >= 7 && patientList.size() / 4 >= size) {
            patientList = new LinkedList<Patient>();
            for (int j = 0; j < tempSize / 2; j++) {
                if (j < store.size()) {
                    if (store.get(j) == null) {
                        patientList.add(j, null);
                    } else {
                        patientList.addLast(store.get(j));
                    }
                } else if (j >= store.size()) {
                    patientList.addLast(null);
                }
            }
        } else {
            patientList = new LinkedList<>();
            for (int j = 0; j < tempSize; j++) {
                if (j < store.size()) {
                    patientList.addLast(store.get(j));
                } else if (j >= store.size()) {
                    patientList.add(j, null);
                }
            }
        }
        return temp;
    }
}
