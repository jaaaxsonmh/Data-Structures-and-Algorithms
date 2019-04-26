package src.assignment2;

/**
 * @author Jack Hosking
 * Student ID: 16932920
 */

import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import java.util.Date;

public class VetGUI extends JPanel {

    private static final String W3C_XML_SCHEMA  = "http://www.w3.org/2001/XMLSchema";
    private static final String JAXP_SCHEMA_LANGUAGE  = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    private JButton newPatient, seeLater, release, loadXML, saveXML, updatePic;
    private JPanel drawPanel;

    private int width = 800;
    private AnimalProcessor processor;


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

    private void updatePanel() {
        if(this.processor!=null){
            remove(this.drawPanel);
            this.drawPanel = this.processor.getNextAnimal().getDisplayPanel();
            add(drawPanel, BorderLayout.CENTER);
            revalidate();
            repaint();
        }
    }

    private void seeLater() {
        AnimalPatient animalPatient = processor.releaseAnimal();
        System.out.println(animalPatient);
        animalPatient.updateDate(new Date());
        animalPatient.setPriority(9);
        processor.addAnimal(animalPatient);
        updatePanel();
    }

    private void newPatient() {
    }

    private void release() {
    }

    private void loadXML() {
        JFileChooser jFileChooser = new JFileChooser(new File("."));
        int j = jFileChooser.showOpenDialog(null);
        if(j == JFileChooser.APPROVE_OPTION){
            File file = jFileChooser.getSelectedFile();
            try {
                DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
                builderFactory.setNamespaceAware(true);
                builderFactory.setValidating(true);
                builderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
                DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
                //parse input stream
                Document document = documentBuilder.parse(file);
                processor = new AnimalProcessor();
                processor.loadAnimalsFromXML(document);
                updatePanel();
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void saveXML() {
        JFileChooser chooser = new JFileChooser(new File("."));
        int i = chooser.showSaveDialog(null);
        if(i==JFileChooser.APPROVE_OPTION) {
            try {
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                factory.setNamespaceAware(true);
                DocumentBuilder documentBuilder = factory.newDocumentBuilder();
                Document newDocument = documentBuilder.getDOMImplementation().createDocument(null, "animals", null);
                newDocument.setXmlVersion("1.0");
                newDocument.setXmlStandalone(true);
                
                Element root = newDocument.getDocumentElement();
                root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
                root.setAttribute("xsi:noNameSpaceSchemaLocation", "animals.xsd");
                
                processor.getWaitList().stream().forEach((animal) -> addAnimal(newDocument, root, animal));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void addAnimal(Document newDocument, Element root, AnimalPatient animal) {
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
        JFrame frame = new JFrame("Jack - VET GUI");

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
