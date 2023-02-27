package org.acme;

 import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.*;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

public class XliffReader {
    public static void main(String[] args){

        String outputFile = "C:\\Users\\rmsivakumar\\IdeaProjects\\Quarkus\\code-with-quarkus\\src\\main\\resources\\messages.xlf";


        try {
            // Set up the DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Parse the XLIFF file
            File inputFile = new File(outputFile);
            Document document = builder.parse(inputFile);

            // Get all the trans-unit elements
            NodeList transUnits = document.getElementsByTagName("trans-unit");

            // Iterate through the trans-unit elements
            for (int i = 0; i < transUnits.getLength(); i++) {
                // Get the current trans-unit element
                Element transUnit = (org.w3c.dom.Element) transUnits.item(i);

                // Get the trans-unit ID
                String id = transUnit.getAttribute("id");

                // Get the source element
                Element source = (org.w3c.dom.Element) transUnit.getElementsByTagName("source").item(0);

                // Get the value of the source element
                String value = source.getTextContent();

                // Print the trans-unit ID and its corresponding value
                System.out.println("Trans-Unit ID: " + id);
                System.out.println("Value in source tag: " + value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
