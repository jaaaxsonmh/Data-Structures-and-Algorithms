package src.assignment2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.*;

public class BinarySearchTree<E> extends AbstractSet<E>
        implements SortedSet<E> {
    private int numElements;
    protected BinaryTreeNode rootNode;
    private Comparator<? super E> comparator;//null for natural ordering
    private E fromElement, toElement; // bounds for visible view of tree

    public BinarySearchTree() {
        super();
        numElements = 0;
        rootNode = null;
        comparator = null;
        fromElement = null;
        toElement = null;
    }

    public BinarySearchTree(Collection<? extends E> c) {
        this();
        for (E element : c)
            add(element);
    }

    public BinarySearchTree(Comparator<? super E> comparator) {
        this();
        this.comparator = comparator;
    }


    public void drawTree(Graphics g, int panel_width) {
        int nodeCount = 0 - (size() / 2);
        if (rootNode != null)
            drawNode(g, rootNode, panel_width / 2, 0, nodeCount);

    }

    private int drawNode(Graphics g, BinaryTreeNode current, int xPosition, int level, int nodeCount) {
        int BOX_SIZE = 40;

        if (current.leftChild != null)
            nodeCount = drawNode(g, current.leftChild, xPosition, level + 1, nodeCount);
        current.x = xPosition + nodeCount * BOX_SIZE;
        current.y = level * 2 * BOX_SIZE + BOX_SIZE;
        nodeCount++;

        if (current.rightChild != null)
            nodeCount = drawNode(g, current.rightChild, xPosition, level + 1, nodeCount);

        g.setColor(Color.red);
        if (current.leftChild != null)
            g.drawLine(current.x, current.y, current.leftChild.x, current.leftChild.y - BOX_SIZE / 2);
        if (current.rightChild != null)
            g.drawLine(current.x, current.y, current.rightChild.x, current.rightChild.y - BOX_SIZE / 2);
        if (!current.visit)
            g.setColor(Color.WHITE);
        else
            g.setColor(Color.YELLOW);
        g.fillRect(current.x - BOX_SIZE / 2, current.y - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
        g.setColor(Color.BLACK);
        g.drawRect(current.x - BOX_SIZE / 2, current.y - BOX_SIZE / 2, BOX_SIZE, BOX_SIZE);
        g.drawString(current.element.toString(), current.x - BOX_SIZE / 4, current.y + BOX_SIZE / 4);
        return nodeCount;
    }


    public BinarySearchTree(SortedSet<E> s) {
        this();
        this.comparator = s.comparator();
        for (E element : s)
            add(element);
    }

    // private constructor used to create a view of a portion of tree
    private BinarySearchTree(BinaryTreeNode rootNode,
                             Comparator<? super E> comparator, E fromElement, E toElement) {
        this(comparator);
        this.rootNode = rootNode;
        this.fromElement = fromElement;
        this.toElement = toElement;
        // calculate the number of elements
        this.numElements = countNodes(rootNode);
    }

    // recursive helper method that counts number of descendants of node
    private int countNodes(BinaryTreeNode node) {
        if (node == null)
            return 0;
        else
            return countNodes(node.leftChild) + 1
                    + countNodes(node.rightChild);
    }

    // helper method that determines whether an element is within the
    // specified view
    private boolean withinView(E element) {
        boolean inside = true;
        if (fromElement != null && compare(element, fromElement) < 0)
            inside = false;
        if (toElement != null && compare(element, toElement) >= 0)
            inside = false;
        return inside;
    }

    // adds the element to the sorted set provided it is not already in
    // the set, and returns true if the sorted set did not already
    // contain the element
    public boolean add(E o) {
        if (!withinView(o))
            throw new IllegalArgumentException("Outside view");
        BinaryTreeNode newNode = new BinaryTreeNode(o);
        boolean added = false;
        if (rootNode == null) {
            rootNode = newNode;
            rootNode.setParent(null);
            added = true;
        } else {  // find where to add newNode
            BinaryTreeNode currentNode = rootNode;
            boolean done = false;
            while (!done) {
                int comparison = compare(o, currentNode.element);
                if (comparison < 0) // newNode is less than currentNode
                {
                    if (currentNode.leftChild == null) {
                        newNode.setParent(currentNode);
                        // add newNode as leftChild
                        currentNode.leftChild = newNode;
                        done = true;
                        added = true;
                    } else
                        currentNode = currentNode.leftChild;
                } else if (comparison > 0)//newNode is greater than currentNode
                {
                    if (currentNode.rightChild == null) {
                        newNode.setParent(currentNode);

                        // add newNode as rightChild
                        currentNode.rightChild = newNode;
                        done = true;
                        added = true;
                    } else
                        currentNode = currentNode.rightChild;
                } else if (comparison == 0) { // newNode equal to currentNode
                    done = true;
                }// no duplicates in this binary tree impl.

            }
        }
        if (added) {
            numElements++;
        }
        return added;
    }

    // performs a comparison of the two elements, using the comparator
    // if not null, otherwise using the compareTo method
    private int compare(E element1, E element2) {
        if (comparator != null)
            return comparator.compare(element1, element2);
        else if (element1 != null && element1 instanceof Comparable)
            return ((Comparable) element1).compareTo(element2); //unchecked
        else if (element2 != null && element2 instanceof Comparable)
            return -((Comparable) element2).compareTo(element1);//unchecked
        else return 0;
    }

    // remove the element from the sorted set and returns true if the
    // element was in the sorted set
    public boolean remove(Object o) {
        boolean removed = false;
        E element = (E) o; // unchecked, could throw exception
        if (!withinView(element))
            throw new IllegalArgumentException("Outside view");
        if (rootNode != null) {  // check if root to be removed
            if (compare(element, rootNode.element) == 0)
                rootNode = makeReplacement(rootNode);
            else {  // search for the element o
                BinaryTreeNode parentNode = rootNode;
                BinaryTreeNode removalNode;
                // determine whether to traverse to left or right of root
                if (compare(element, rootNode.element) < 0)
                    removalNode = rootNode.leftChild;
                else // compare(element, rootNode.element)>0
                    removalNode = rootNode.rightChild;
                while (removalNode != null && !removed) {  // determine whether the removalNode has been found
                    int comparison = compare(element, removalNode.element);
                    if (comparison == 0) {
                        if (removalNode == parentNode.leftChild)
                            parentNode.leftChild
                                    = makeReplacement(removalNode);
                        else // removalNode==parentNode.rightChild
                            parentNode.rightChild
                                    = makeReplacement(removalNode);
                        removed = true;
                    } else // determine whether to traverse to left or right
                    {
                        parentNode = removalNode;
                        if (comparison < 0)
                            removalNode = removalNode.leftChild;
                        else // comparison>0
                            removalNode = removalNode.rightChild;
                    }
                }
            }
        }
        if (removed) numElements--;
        return removed;
    }

    // helper method which removes removalNode (presumed not null) and
    // returns a reference to node that should take place of removalNode
    private BinaryTreeNode makeReplacement(BinaryTreeNode removalNode) {
        BinaryTreeNode replacementNode = null;
        // check cases when removalNode has only one child
        if (removalNode.leftChild != null && removalNode.rightChild == null)
            replacementNode = removalNode.leftChild;
        else if (removalNode.leftChild == null
                && removalNode.rightChild != null)
            replacementNode = removalNode.rightChild;
            // check case when removalNode has two children
        else if (removalNode.leftChild != null
                && removalNode.rightChild != null) {  // find the inOrderTraversal successor and use it as replacementNode
            BinaryTreeNode parentNode = removalNode;
            replacementNode = removalNode.rightChild;
            if (replacementNode.leftChild == null)
                // replacementNode can be pushed up one level to replace
                // removalNode, move the left child of removalNode to be
                // the left child of replacementNode
                replacementNode.leftChild = removalNode.leftChild;
            else {  //find left-most descendant of right subtree of removalNode
                do {
                    parentNode = replacementNode;
                    replacementNode = replacementNode.leftChild;
                }
                while (replacementNode.leftChild != null);
                // move the right child of replacementNode to be the left
                // child of the getParent of replacementNode
                parentNode.leftChild = replacementNode.rightChild;
                // move the children of removalNode to be children of
                // replacementNode
                replacementNode.leftChild = removalNode.leftChild;
                replacementNode.rightChild = removalNode.rightChild;
            }
        }
        // else both leftChild and rightChild null so no replacementNode
        return replacementNode;
    }

    // Purpose is to take a String (Object) and
    // and find its Node counterpart.
    // Catch null pointers if not in bst, or no input.
    public BinaryTreeNode findNode(Object o) {
        boolean found = false;
        E element = (E) o; // unchecked, could throw exception
        BinaryTreeNode currentNode = rootNode;
        try {
            while (!found && currentNode != null) {
                int comparison = compare(currentNode.element, element);
                if (comparison == 0) {
                    found = true;
                } else if (comparison < 0) {
                    // navigate to the right
                    currentNode = currentNode.rightChild;
                } else {
                    // navigate to the left
                    currentNode = currentNode.leftChild;
                }
            }

        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught");
        }

        return currentNode;
    }

    public Iterator<E> iterator() {
        return new BinaryTreeIterator(rootNode);
    }

    // returns the number of elements in the tree
    public int size() {
        return numElements;
    }

    // removes all elements from the collection
    public void clear() {
        rootNode = null; // all nodes will be garbage collected as well
    }

    // overridden method with an efficient O(log n) search algorithm
    // rather than the superclasses O(n) linear search using iterator
    public boolean contains(Object o) {
        boolean found = false;
        E element = (E) o; // unchecked, could throw exception
        if (!withinView(element))
            return false;
        BinaryTreeNode currentNode = rootNode;
        while (!found && currentNode != null) {
            int comparison = compare(currentNode.element, element);
            if (comparison == 0)
                found = true;
            else if (comparison < 0)
                currentNode = currentNode.rightChild;
            else // comparison>0
                currentNode = currentNode.leftChild;
        }
        return found;
    }

    // returns the Comparator used to compare elements or null if
    // the element natural ordering is used
    public Comparator<? super E> comparator() {
        return comparator;
    }

    // returns the first (lowest) element currently in sorted set that
    // is at least as big as fromElement, returns null if none found
    public E first() {
        if (rootNode == null)
            throw new NoSuchElementException("empty tree");
        // find the least descendant of rootNode that is at least
        // as big as fromElement by traversing down tree from root
        BinaryTreeNode currentNode = rootNode;
        BinaryTreeNode leastYetNode = null; // smallest found so far
        while (currentNode != null) {
            if (compare(currentNode.element, fromElement) >= 0) {
                if (compare(currentNode.element, toElement) < 0)
                    leastYetNode = currentNode;
                // move to the left child to see if a smaller element okay
                // since all in right subtree will be larger
                currentNode = currentNode.leftChild;
            } else // compare(currentNode.element, fromElement)<0
            {  // move to the right child since this element too small
                // so all in left subtree will also be too small
                currentNode = currentNode.rightChild;
            }
        }
        if (leastYetNode == null) // no satisfactory node found
            return null;
        else
            return leastYetNode.element;
    }

    public SortedSet<E> headSet(E toElement) {
        return subSet(null, toElement);
    }

    // returns the last (highest) element currently in sorted set that
    // is less than toElement, return null if none found
    public E last() {
        if (rootNode == null)
            throw new NoSuchElementException("empty tree");
        // find the greatest descendant of rootNode that is less than
        // toElement by traversing down tree from root
        BinaryTreeNode currentNode = rootNode;
        BinaryTreeNode greatestYetNode = null; // greatest found so far
        while (currentNode != null) {
            if (compare(currentNode.element, toElement) < 0) {
                if (compare(currentNode.element, fromElement) >= 0)
                    greatestYetNode = currentNode;
                // move to the right child to see if a greater element okay
                // since all in left subtree will be smaller
                currentNode = currentNode.rightChild;
            } else // compare(currentNode.element, toElement)>=0
            {  // move to the left child since this element too large
                // so all in right subtree will also be too large
                currentNode = currentNode.leftChild;
            }
        }
        if (greatestYetNode == null) // no satisfactory node found
            return null;
        else
            return greatestYetNode.element;
    }


    public SortedSet<E> subSet(E fromElement, E toElement) {
        return new BinarySearchTree<E>(rootNode, comparator, fromElement,
                toElement);
    }

    public SortedSet<E> tailSet(E fromElement) {
        return subSet(fromElement, null);
    }


    // outputs the elements stored in the full binary tree (not just
    // the view) using inOrderTraversal traversal
    public String toString() {
        return "Tree: " + rootNode;
    }

    public static void main(String[] args) {  // create the binary search tree
        SortedSet<String> tree = new BinarySearchTree<String>();
        // build the tree
        tree.add("cow");
        tree.add("fly");
        tree.add("dog");
        tree.add("bat");
        tree.add("fox");
        tree.add("cat");
        tree.add("eel");
        tree.add("ant");
        System.out.println("Original Tree: " + tree);
        tree.remove("owl");
        tree.remove("cow");
        tree.add("owl");
        System.out.println("Modified Tree: " + tree);
        SortedSet<String> subtree = tree.subSet("cat", "fox");
        System.out.print("Subtree iteration: ");
        Iterator<String> i = subtree.iterator();
        while (i.hasNext()) {
            System.out.print(i.next());
            if (i.hasNext()) System.out.print(", ");
        }
        System.out.println();
        System.out.println("first element in subtree: " + subtree.first());
        System.out.println("last element in subtree: " + subtree.last());
    }

    public void startInOrder() {
        new Thread(() -> inOrderTraversal(rootNode)).start();
    }

    public void startLevelOrder() {
        new Thread(this::levelOrderTraversal).start();
    }

    public void resetVisited(BinaryTreeNode node) {
        //if node has no children (or root) then break.
        if(node == null)
            return;

        // set the node to be un-visited
        node.visit = false;
        //recursion for the nodes children
        resetVisited(node.leftChild);
        resetVisited(node.rightChild);
    }

    public void leftRotate(BinaryTreeNode rotateNode) {

        if (rotateNode.rightChild == null) {
            System.out.println("Can not perform left rotation, not right child.");
            return;
        }

        BinaryTreeNode oldRight = rotateNode.rightChild;
        rotateNode.rightChild = oldRight.leftChild;

        if (rotateNode.parent == null) {
            rootNode = oldRight;
        } else if (rotateNode.parent.leftChild == rotateNode) {
            rotateNode.parent.leftChild = oldRight;
        } else {
            rotateNode.parent.rightChild = oldRight;
        }

        oldRight.leftChild = rotateNode;
        rotateNode.setParent(oldRight);
    }


    public void rightRotate(BinaryTreeNode rotateNode) {

        if (rotateNode.leftChild == null) {
            System.out.println("Can not perform left rotation, not right child.");
            return;
        }

        BinaryTreeNode oldLeft = rotateNode.leftChild;
        rotateNode.leftChild = oldLeft.rightChild;

        if (rotateNode.parent == null) {
            rootNode = oldLeft;
        } else if (rotateNode.parent.leftChild == rotateNode) {
            rotateNode.parent.leftChild = oldLeft;
        } else {
            rotateNode.parent.rightChild = oldLeft;
        }

        oldLeft.rightChild = rotateNode;
        rotateNode.setParent(oldLeft);

    }

    public void inOrderTraversal(BinaryTreeNode node) {
        try {
            if (node == null)
                return;

            inOrderTraversal(node.leftChild);
            Thread.sleep(500);
            node.visit = true;
            System.out.print(node.element + " ");
            inOrderTraversal(node.rightChild);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void levelOrderTraversal() {
        // We want to recurse the node for every level of both the right and left subtrees
        for (int i = 1; i <= height(rootNode); i++) {
            levelOrderNode(rootNode, i);
        }
    }


    public void levelOrderNode(BinaryTreeNode node, int level) {
        try {
            if (node == null) {
                return;
            }

            if (level == 1) {
                System.out.print(node.element + " ");
                Thread.sleep(500);

                node.visit = true;
            } else if (level > 1) {
                levelOrderNode(node.leftChild, level - 1);
                levelOrderNode(node.rightChild, level - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // helper method that determines the height of the subtrees to the lowest/ farthest leaf node
    public int height(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        } else {
            return Math.max(height(root.leftChild), height(root.rightChild)) + 1;
        }
    }

    // inner class that represents a node in the binary tree
    // where each node consists of the element and links to
    // left child and right child (no need for link to getParent)
    protected class BinaryTreeNode {
        public BinaryTreeNode leftChild, rightChild, parent;
        public E element;
        public int x, y;
        public boolean visit;

        public BinaryTreeNode(E element) {
            this.element = element;
            leftChild = null;
            rightChild = null;
        }

        public void setParent(BinaryTreeNode parent) {
            this.parent = parent;
        }

        public BinaryTreeNode getParent() {
            return parent;
        }


        // returns a string representation of the node and
        // its children using inOrderTraversal (left-this-right) traversal
        public String toString() {
            String output = "[";
            if (leftChild != null)
                output += "" + leftChild;
            output += "" + element;
            if (rightChild != null)
                output += "" + rightChild;
            output += "]";
            return output;
        }
    }

    // inner class that represents an Iterator for a binary tree
    private class BinaryTreeIterator implements Iterator<E> {
        private LinkedList<E> list;
        private Iterator<E> iterator;

        public BinaryTreeIterator(BinaryTreeNode rootNode) {  // puts the elements in a linked list using inOrderTraversal traversal
            list = new LinkedList<E>();
            traverseInOrder(rootNode);
            iterator = list.iterator();
        }

        // recursive helper method that traverses the subtree from node
        // adding the elements to the list collection
        private void traverseInOrder(BinaryTreeNode node) {
            if (node != null) {
                traverseInOrder(node.leftChild);
                if (withinView(node.element))
                    list.add(node.element);
                traverseInOrder(node.rightChild);
            }
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }

        public E next() {
            return iterator.next();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }


    }
}