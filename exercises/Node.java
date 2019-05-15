package exercises;

public class Node {
    private String name;
    private int id;
    private String birthDate;
    private Node next;  //the link referencing to the next node in the link list.

    public Node(int id, String name, String birthDate, Node next) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.next = next;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Node getNext() {
        return next;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(this.id);
        return s.toString();
    }
}