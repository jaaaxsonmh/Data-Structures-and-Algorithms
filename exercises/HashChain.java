package exercises;

public class HashChain {
    final static int CAPACITY = 5;
    protected SLinkedList[] HashTable;
    protected int size;


    public HashChain(int cap) {
        size = 0;
        HashTable = new SLinkedList[cap];

        for (int i = 0; i < cap; i++) {
            HashTable[i] = new SLinkedList();

        }
    }

    public int getSize() {
        return size;
    }

    public int hash(int key) {
        return ((7 * key) + 29) % 5;
    }


    public Node getStudent(int key) {
        Node temp = HashTable[hash(key)].search(key);

        if (temp == null)
            return null;

        else {
            return temp;
        }

    }


    public int put(Node NewOne) {
        Node temp = HashTable[hash(NewOne.getId())].search(NewOne.getId());

        if (temp == null) {
            HashTable[hash(NewOne.getId())].addLast(NewOne);
            size++;
            return 0;
        } else {
            int oldID = temp.getId();
            HashTable[hash(NewOne.getId())].addAfter(temp, NewOne);
            HashTable[hash(NewOne.getId())].delete(temp.getId());
            return oldID;
        }

    }

    //removes any given node from the table using their id as a key
    public Node remove(int key) {
        Node temp = HashTable[hash(key)].delete(key);

        if (temp == null)
            return null;

        else {
            size--;
            return temp;

        }


    }

    //toString formatted to look like a hash table
    public String toString() {

        StringBuilder all = new StringBuilder();

        for (int i = 0; i < CAPACITY; i++) {
            all.append(HashTable[i].toString() + " \n");
        }

        return all.toString();


    }


}