package src.assignment2;

/**
 * @author Jack Hosking
 * Student ID: 16932920
 */

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class VetGUI extends JPanel {

    private static final String W3C_XML_SCHEMA  = "http://www.w3.org/2001/XMLSchema";
    private static final String JAXP_SCHEMA_LANGUAGE  = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    private JButton newPatient, seeLater, release, loadXML, saveXML, updatePic;
    private JPanel drawPanel;

    private int width = 800;


    public VetGUI() {
        super(new BorderLayout());



        drawPanel = new JPanel();
        drawPanel.setPreferredSize(new Dimension(width, 600));
        drawPanel.setBackground(Color.WHITE);
        add(drawPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        newPatient = new JButton("New Patient");
        newPatient.addActionListener((action) -> newPatient());
        buttonPanel.add(newPatient);

        seeLater = new JButton("See Later");
        seeLater.addActionListener((action) -> seeLater());
        buttonPanel.add(seeLater);

        release = new JButton("Release");
        release.addActionListener((action) -> release());
        buttonPanel.add(release);

        loadXML = new JButton("Load XML");
        loadXML.addActionListener((action) -> loadXML());
        buttonPanel.add(loadXML);

        saveXML = new JButton("Save XML");
        saveXML.addActionListener((action) -> saveXML());
        buttonPanel.add(saveXML);

        updatePic = new JButton("Update Pic");
        updatePic.addActionListener((action) -> updatePic());
        buttonPanel.add(updatePic);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void seeLater() {
    }

    private void newPatient() {
    }

    private void release() {
    }

    private void loadXML() {
    }

    private void saveXML() {
    }

    private void updatePic() {
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

        }
    }


    public static void main(String[] args) {
        frame = new JFrame("Jack - VET GUI");

        // kill all threads when frame closes
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new VetGUI());
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
