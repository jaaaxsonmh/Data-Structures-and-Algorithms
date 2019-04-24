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

public class VetGUI extends JPanel implements ActionListener {

    private static final String W3C_XML_SCHEMA  = "http://www.w3.org/2001/XMLSchema";

    private static JFrame frame;
    private JButton newPatient, seeLater, release, loadXML, saveXML, updatePic;
    private DrawPanel drawPanel;
    private JFileChooser jFileChooser;
    private Document currentDocument;
    private AnimalProcessor animal;

    private int width = 800;


    public VetGUI() {
        super(new BorderLayout());

        jFileChooser = new JFileChooser();

        drawPanel = new DrawPanel();
        add(drawPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        newPatient = new JButton("New Patient");
        newPatient.addActionListener(this);
        buttonPanel.add(newPatient);

        seeLater = new JButton("See Later");
        seeLater.addActionListener(this);
        buttonPanel.add(seeLater);

        release = new JButton("Release");
        release.addActionListener(this);
        buttonPanel.add(release);

        loadXML = new JButton("Load XML");
        loadXML.addActionListener(this);
        buttonPanel.add(loadXML);

        saveXML = new JButton("Save XML");
        saveXML.addActionListener(this);
        buttonPanel.add(saveXML);

        updatePic = new JButton("Update Pic");
        updatePic.addActionListener(this);
        buttonPanel.add(updatePic);

        add(buttonPanel, BorderLayout.SOUTH);


        Timer timer = new Timer(25, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == loadXML) {
            int val = jFileChooser.showOpenDialog(this);
            if (val == JFileChooser.APPROVE_OPTION) {
                File file = jFileChooser.getSelectedFile();
                try {
                    drawPanel.removeAll();
                    DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                    builderFactory.setNamespaceAware(true);
                    builderFactory.setValidating(true);
                    builderFactory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", W3C_XML_SCHEMA);
                    DocumentBuilder builder = builderFactory.newDocumentBuilder();
                    // parse the input stream
                    currentDocument = builder.parse(file);
                    frame.pack();
                }
                catch (FactoryConfigurationError ex) {
                    System.out.println(ex.getMessage());
                }
                catch (ParserConfigurationException ex) {
                    System.out.println(ex.getMessage());
                }
                catch (SAXException ex) {
                    System.out.println(ex.getMessage());
                }
                catch (IOException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
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
