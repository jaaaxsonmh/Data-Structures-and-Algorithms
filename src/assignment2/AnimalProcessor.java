package src.assignment2;

import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.print.Book;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;

public class AnimalProcessor {

    private DOMUtilities util = new DOMUtilities();
    private static final String JAXP_SCHEMA_LANGUAGE  = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
    private static final String W3C_XML_SCHEMA  = "http://www.w3.org/2001/XMLSchema";
    private String description;
    private String image;

    private LinkedHashMap<Integer, AnimalPatient> waitlist;


    public AnimalProcessor() {
        super();
        waitlist = new LinkedHashMap<>();

    }

    public void addAnimal(AnimalPatient animal) {

    }

    public AnimalPatient getNextAnimal() {

        return null;
    }

    public AnimalPatient releaseAnimal() {

        return null;
    }

    public int animalsLeftToProcess() {
        return 0;
    }

    public void setDescription(String description) {
        this.description = description.trim();
    }

    public void loadAnimalsFromXML(Document document) {
        document.getDocumentElement().normalize();
        Node rootXMLNode = document.getDocumentElement();
        Collection<Node> animal = util.getAllChildNodes(rootXMLNode, "Animal");
        for(Node z : util.getAllChildNodes(rootXMLNode, "description")) {
            setDescription(util.getTextContent(z));
        }
        System.out.println(animal);
        String name, species = "";
        int priority = 1;

        Collection<String> animals = new ArrayList<>();
        for(Node i : animal) {
            name = util.getAttributeString(i, "name");
            species = util.getAttributeString(i, "species");
            priority = Integer.parseInt(util.getAttributeString(i, "priority"));
            image = util.getAttributeString(i, "picURL");

            System.out.println(name);
//            for(Node j : util.getAllChildNodes(i, "title")) {
//                title = util.getTextContent(j);
//            }
//
//            authors.clear();
//            for(Node a : util.getAllChildNodes(i, "author")) {
//                authors.add(util.getTextContent(a));
//            }
//            for(Node b : util.getAllChildNodes(i, "pub")) {
//                pub = new Publisher(util.getAttributeString(b, "name"), util.getAttributeString(b, "website"));
//            }
            waitlist.put(priority, new AnimalPatient(priority, name, species));
        }
    }

    public static void main(String args[])
    {
        AnimalProcessor animalProcessor = new AnimalProcessor();
        System.out.println("============== Animal Processor Test ===============");


        String file = "src/assignment2/AnimalsInVet.xml";
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            builderFactory.setValidating(true);
            builderFactory.setAttribute(JAXP_SCHEMA_LANGUAGE,W3C_XML_SCHEMA);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();
            // parse the input stream
            Document document = builder.parse(file);
            animalProcessor.loadAnimalsFromXML(document);

        }
        catch (FactoryConfigurationError | ParserConfigurationException | SAXException | IOException e) {
            System.out.println(e.getMessage());
        }



    }
}
