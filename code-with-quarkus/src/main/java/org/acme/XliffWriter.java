package org.acme;



import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


public class XliffWriter {

    private XliffWriter(){
        throw new IllegalStateException("Utility class");
    }

    public static void Create(List<RTM_TRANSLATION> Draft,List<RTM_TRANSLATION> RTM_ID,String sourceLanguage) throws IOException, TransformerException, ParserConfigurationException {


        String outputFile = "C:\\Users\\rmsivakumar\\IdeaProjects\\Quarkus\\code-with-quarkus\\src\\main\\resources\\messages.xlf";


        Iterator key1 = RTM_ID.iterator();
        Iterator value1 = Draft.iterator();




        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        DocumentBuilder builder = factory.newDocumentBuilder();

        Document doc = builder.newDocument();

        Element xliff = doc.createElement("xliff");

        xliff.setAttribute("version", "1.2");
        xliff.setAttribute("xmlns", "urn:oasis:names:tc:xliff:document:1.2");


        Element file = doc.createElement("file");
        file.setAttribute("source-language", sourceLanguage);
        file.setAttribute("original", "RTM_TRANSLATION");


         Element body = doc.createElement("body");

         String DT = "datatype";
         while (key1.hasNext() && value1.hasNext()) {
             String key = key1.next()+"";
             String value = value1.next()+"";
             Element transUnit = doc.createElement("trans-unit");
             transUnit.setAttribute("id", key);

             Element source = doc.createElement("source");
             source.setTextContent(value);

             if (key.endsWith(".html") || key.endsWith(".htm")) {
                transUnit.setAttribute(DT, "html");
            } else if (key.endsWith(".xml")) {
                transUnit.setAttribute(DT, "xml");
            } else {
                transUnit.setAttribute(DT, "plaintext");
            }

             transUnit.appendChild(source);

             body.appendChild(transUnit);
        }

         file.appendChild(body);

         xliff.appendChild(file);

         doc.appendChild(xliff);
         TransformerFactory transformerFactory = TransformerFactory.newInstance();
         Transformer transformer = transformerFactory.newTransformer();
         transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
         transformer.setOutputProperty(OutputKeys.INDENT, "yes");
         transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
         DOMSource source = new DOMSource(doc);
         StreamResult result = new StreamResult(new File(outputFile));
         transformer.transform(source, result);
    }



}
