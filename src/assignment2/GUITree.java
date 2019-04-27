package src.assignment2;

/**
 * @author Jack Hosking
 * Student ID: 16932920
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class GUITree extends JPanel implements ActionListener {

    private DrawPanel drawPanel;

    private BinarySearchTree<String> binarySearchTree = new BinarySearchTree<>();
    private int width = 800;


    public GUITree() {
        super(new BorderLayout());

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addNode = new JButton("Add Node");
        addNode.addActionListener((a) -> addNode());
        buttonPanel.add(addNode);

        JButton removeNode = new JButton("Remove Node");
        removeNode.addActionListener((a) -> removeNode());
        buttonPanel.add(removeNode);

        JButton levelOrderTraverse = new JButton("Level Order Traverse");
        levelOrderTraverse.addActionListener((a) -> levelOrderTraverse());
        buttonPanel.add(levelOrderTraverse);

        JButton inOrderTraverse = new JButton("In Order Traverse");
        inOrderTraverse.addActionListener((a) -> inOrderTraverse());
        buttonPanel.add(inOrderTraverse);

        JButton leftRotate = new JButton("Left Rotate");
        leftRotate.addActionListener((a) -> leftRotate());
        buttonPanel.add(leftRotate);

        JButton rightRotate = new JButton("Right Rotate");
        rightRotate.addActionListener((a) -> rightRotate());
        buttonPanel.add(rightRotate);

        JButton resetVisited = new JButton("Reset Visited");
        resetVisited.addActionListener((a) -> resetVisited());
        buttonPanel.add(resetVisited);

        add(buttonPanel, BorderLayout.SOUTH);

        populateCollection();

        Timer timer = new Timer(25, this);
        timer.start();
    }

    private void resetVisited() {
        binarySearchTree.resetVisited(binarySearchTree.rootNode);
    }

    private void rightRotate() {
        String rotateNode = JOptionPane.showInputDialog("Right Rotation");
        BinarySearchTree<String>.BinaryTreeNode node = binarySearchTree.findNode(rotateNode);
        binarySearchTree.rightRotate(node);
        System.out.println("\nRight Rotate: " + rotateNode);
    }


    private void leftRotate() {
        String rotateNode = JOptionPane.showInputDialog("Left Rotation");
        BinarySearchTree<String>.BinaryTreeNode node = binarySearchTree.findNode(rotateNode);
        binarySearchTree.leftRotate(node);
        System.out.println("\nLeft Rotate: " + rotateNode);
    }

    private void inOrderTraverse() {
        System.out.println();
        System.out.print("In Order Traversal: ");
        binarySearchTree.startInOrder();
    }

    private void levelOrderTraverse() {
        System.out.println();
        System.out.print("Level Order Traversal: ");
        binarySearchTree.startLevelOrder();
    }

    private void removeNode() {
        String detachNode = JOptionPane.showInputDialog("Remove Node");
        binarySearchTree.remove(detachNode);
        System.out.println("\nRemove: " + detachNode);
    }

    private void addNode() {
        String attachNode = JOptionPane.showInputDialog("Add Node");
        binarySearchTree.add(attachNode);
        System.out.println("\nAdd: " + attachNode);
    }

    private void populateCollection() {
        binarySearchTree.add("cat");
        binarySearchTree.add("ant");
        binarySearchTree.add("cow");
        binarySearchTree.add("eel");
        binarySearchTree.add("zebra");
        binarySearchTree.add("dog");
        binarySearchTree.add("fly");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        drawPanel.repaint();

    }

    private class DrawPanel extends JPanel {

        public DrawPanel() {
            super();
            int height = 600;
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            binarySearchTree.drawTree(g, width);
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Jack - GUI TREE");

        // kill all threads when frame closes
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new GUITree());
        frame.pack();

        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = frame.getSize();
        frame.setLocation((screenDimension.width - frameDimension.width) / 2,
                (screenDimension.height - frameDimension.height) / 2);
        frame.setVisible(true);
        // now display something while the main thread is still alive
    }
}
