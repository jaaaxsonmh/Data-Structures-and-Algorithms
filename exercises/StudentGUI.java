package exercises;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.io.*;


public class StudentGUI extends JPanel {



    private JPanel drawPanel;
    private JFrame frame;
    private JLabel consoleLog, printOuts;

    private HashChain Hashtable = new HashChain(150);

    private int width = 800;


    public StudentGUI() {
        super(new BorderLayout());

        drawPanel = new JPanel();
        drawPanel.setLayout(new GridBagLayout());
        consoleLog = new JLabel("Please enter a Student", SwingConstants.CENTER);
        printOuts = new JLabel("Nothing queried", SwingConstants.CENTER);
        drawPanel.add(printOuts);
        consoleLog.setFont(new Font("Comic Sans", Font.BOLD, 24));
        add(consoleLog, BorderLayout.NORTH);
        drawPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        drawPanel.setPreferredSize(new Dimension(width, 600));
        drawPanel.setBackground(Color.WHITE);
        add(drawPanel);

        JPanel buttonPanel = new JPanel();
        JButton newStudent = new JButton("New Student");
        newStudent.addActionListener((action) -> newStudent());
        buttonPanel.add(newStudent);

        JButton deleteStudent = new JButton("Delete Student");
        deleteStudent.addActionListener((action) -> deleteStudent());
        buttonPanel.add(deleteStudent);

        JButton searchStudent = new JButton("Search Student");
        searchStudent.addActionListener((action) -> searchStudent());
        buttonPanel.add(searchStudent);

        JButton showAllStudents = new JButton("Show all students");
        showAllStudents.addActionListener((action) -> updateTable());
        buttonPanel.add(showAllStudents);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void deleteStudent() {
        consoleLog.setText("Enter the student id");

        int studentID = getStudentID();

        Node target = Hashtable.getStudent(studentID);
        if (target == null) {
            consoleLog.setText("No such student.");
        } else {
            Hashtable.remove(studentID);
            consoleLog.setText("The student has been deleted successfully");
        }
        updateTable();
    }

    private int getStudentID() {
        int studentID = Integer.parseInt((String) JOptionPane.showInputDialog(
                frame,
                "Enter a student ID",
                "Enter a student ID",
                JOptionPane.PLAIN_MESSAGE,
                null
                ,
                null
                ,
                "Enter a student ID"));

        return studentID;
    }

    private void searchStudent() {
        consoleLog.setText("Enter a student ID");
        int studentID = getStudentID();
        Node target = Hashtable.getStudent(studentID);
        if (target == null)
            consoleLog.setText("No student exists");
        else {
            consoleLog.setText("Found student");
        }

        printOuts.setText("Student id: " + target.getId() + " Name: " + target.getName() + " Birthdate: " + target.getBirthDate());
    }

    private void newStudent() {
        consoleLog.setText("Waiting for new Student");

        int studentID = generateStudentID();
        Node target = Hashtable.getStudent(studentID);

        String name = (String) JOptionPane.showInputDialog(
                frame,
                "Enter new student name.",
                "Enter a Student name",
                JOptionPane.PLAIN_MESSAGE,
                null
                ,
                null
                ,
                "Enter name");

        String birthdate = (String) JOptionPane.showInputDialog(
                frame,
                "Enter new Student birthdate.",
                "Enter a Student birthdate",
                JOptionPane.PLAIN_MESSAGE,
                null
                ,
                null
                ,
                "Enter birthdate");

        if (target == null) {
            Hashtable.put(new Node(studentID, name, birthdate, null));
            consoleLog.setText("Student succesfully added with ID: " + studentID);
        } else {
            Hashtable.put(new Node(studentID, name, birthdate, null));
            consoleLog.setText("Existing Student, entry has been updated");
        }
        updateTable();
    }

    private void updateTable() {
        printOuts.setText(Hashtable.toString());
    }

    private int generateStudentID() {
        Random rand = new Random();
        return rand.nextInt(1000);
    }

    public static void main(String[] args) {
        StudentGUI s = new StudentGUI();
        s.frame = new JFrame("Jack - Student GUI");

        // kill all threads when frame closes
        s.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        s.frame.getContentPane().add(s);
        s.frame.pack();

        // position the frame in the middle of the screen
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenDimension = tk.getScreenSize();
        Dimension frameDimension = s.frame.getSize();
        s.frame.setLocation((screenDimension.width - frameDimension.width) / 2,
                (screenDimension.height - frameDimension.height) / 2);
        s.frame.setVisible(true);
        // now display something while the main thread is still alive
    }
}