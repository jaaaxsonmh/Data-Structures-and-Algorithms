package exercises;

public class SLinkedList {
    protected Node head;
    protected long size;

    public SLinkedList() {
        head = null;
        size = 0;
    }

    public void addFirst(Node v) {
        if (v == null)
            return;

        v.setNext(head);
        head = v;
        size++;
    }

    public void addLast(Node v) {
        if (size == 0)
            addFirst(v);
        else {
            Node cur;
            for (cur = head; cur.getNext() != null; cur = cur.getNext())
                ;
            v.setNext(null);
            cur.setNext(v);
            size++;
        }
    }

    public void removeFirst() {
        if (size == 0)
            return;

        Node old_head = head;
        head = head.getNext();
        size--;
        old_head.setNext(null);

    }

    public void addAfter(Node u, Node v) {
        if (u == null)
            return;

        v.setNext(u.getNext());
        u.setNext(v);
        size++;
    }

    public String toString() {
        StringBuilder all = new StringBuilder();
        for (Node student = head; student != null; student = student.getNext())
            all.append("(" + student.getId() + ", " + student.getName().toUpperCase() + ", " + student.getBirthDate() + ")");
        return all.toString();
    }

    public Node search(int key) {
        if (size == 0)
            return null;
        if (head == null)
            return null;
        if (head.getId() == key)
            return head;

        else {
            for (Node cur = head.getNext(); cur != null; cur = cur.getNext()) {
                if (cur.getId() == key)
                    return cur;
            }
        }

        return null;
    }


    public Node delete(int key) {
        if (size == 0)
            return null;

        if (head.getId() == key) {
            Node old_head = head;
            removeFirst();
            return old_head;
        } else {
            for (Node cur = head.getNext(); cur != null; cur = cur.getNext()) {
                if (cur.getId() == key) {
                    Node prev = head;
                    for (; prev.getNext() != cur; prev = prev.getNext()) ;
                    {
                    }
                    prev.setNext(cur.getNext());
                    cur.setNext(null);
                    return cur;
                }
            }
        }
        Node d = new Node(0, null, null, null);
        return d;
    }

}
