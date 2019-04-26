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
import java.util.TreeSet;

public class AnimalProcessor {

    private TreeSet<AnimalPatient> waitlist = new TreeSet<AnimalPatient>();


    public AnimalProcessor() {
    }

    public void addAnimal(AnimalPatient animal) {
        waitlist.add(animal);
    }

    public AnimalPatient getNextAnimal() {
        return  waitlist.first();
    }

    public AnimalPatient releaseAnimal() {

        return null;
    }

    public int animalsLeftToProcess() {
        return 0;
    }

    public void setDescription(String description) {
    }

    public void loadAnimalsFromXML(Document document) {

    }

    public static void main(String args[])
    {
        AnimalProcessor animalProcessor = new AnimalProcessor();
        System.out.println("============== Animal Processor Test ===============");




    }
}
