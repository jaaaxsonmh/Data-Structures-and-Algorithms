package src.assignment2;

/*
 * author Jack Hosking
 * Student ID: 16932920
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class GUITree extends JPanel implements ActionListener {

    private JButton addNode, removeNode, levelOrderTraverse, inOrderTravers, leftRotate, rightRotate;
    private DrawPanel drawPanel;
    private String attachNode, detachNode, rotateNode;

    private BinarySearchTree<String> bst = new BinarySearchTree<>();

    private int width =800;
    private int height = 600;


    public GUITree() {
        super(new BorderLayout());

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addNode = new JButton("Add Node");
        addNode.addActionListener(this);
        buttonPanel.add(addNode);

        removeNode = new JButton("Remove Node");
        removeNode.addActionListener(this);
        buttonPanel.add(removeNode);

        levelOrderTraverse = new JButton("Level Order Traverse");
        levelOrderTraverse.addActionListener(this);
        buttonPanel.add(levelOrderTraverse);

        inOrderTravers = new JButton("In Order Traverse");
        inOrderTravers.addActionListener(this);
        buttonPanel.add(inOrderTravers);

        leftRotate = new JButton("Left Rotate");
        leftRotate.addActionListener(this);
        buttonPanel.add(leftRotate);

        rightRotate = new JButton("Right Rotate");
        rightRotate.addActionListener(this);
        buttonPanel.add(rightRotate);

        add(buttonPanel, BorderLayout.SOUTH);

        populateCollection();

        Timer timer = new Timer(25, this);
        timer.start();
    }

    private void populateCollection() {
        bst.add("cat");
        bst.add("ant");
        bst.add("cow");
        bst.add("eel");
        bst.add("zebra");
        bst.add("dog");
        bst.add("fly");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == addNode) {
            attachNode = JOptionPane.showInputDialog("Add Node");
            bst.add(attachNode);
            System.out.println("Add: " + attachNode);

        }

        if(source == removeNode) {
            detachNode = JOptionPane.showInputDialog("Remove Node");
            bst.remove(detachNode);
            System.out.println("Remove: " + detachNode);
        }

        if(source == leftRotate) {
            rotateNode = JOptionPane.showInputDialog("Left Rotation");
            bst.findNode(rotateNode, true);
            System.out.println("Left Rotate: " + rotateNode);

        }

        if(source == rightRotate) {
            rotateNode = JOptionPane.showInputDialog("Right Rotation");
            bst.findNode(rotateNode, false);
            System.out.println("Right Rotate: " + rotateNode);

        }


        drawPanel.repaint();
    }

    private class DrawPanel extends JPanel {

        public DrawPanel() {
            super();
            setPreferredSize(new Dimension(width, height));
            setBackground(Color.WHITE);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            bst.drawTree(g, width);
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Jack - GUI TREE");

        // kill all threads when frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
