package org.deals.framework.util;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

public class XmlUtils {


	/**
	 * Effettua l'indentazione del codice xml passato in ingresso
	 * @param xml
	 * @return
	 */
	public static String prettyPrint(String xml) {
        StringWriter wr = new StringWriter();
		try {
			DocumentBuilderFactory fac = DocumentBuilderFactory.newInstance();
			Document doc = fac.newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
			OutputFormat of = new OutputFormat(doc);
			of.setEncoding("iso-8859-1");
			of.setIndenting(true);
			of.setIndent(2);
			of.setLineWidth(20);
			of.setPreserveEmptyAttributes(true);
			of.setPreserveSpace(false);
            XMLSerializer serializer = new XMLSerializer(of);
            serializer.setOutputCharStream(wr);
            serializer.serialize(doc);
            wr.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return wr.toString();		
	}
	
	
	
	/**
	 * Effettua il parsing del file XML passato in ingresso, ed eventualmente effettua la validazione
	 * del file
	 * @param filename file di cui effettuare il parsing
	 * @param validating true se si vuole la validazione, false altrimenti
	 * @return Document ottenuto dal parsing del file XML
	 */
    public static Document parseXmlFile(String filename, boolean validating) {
        try {
            // Create a builder factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(validating);
            
            // Create the builder and parse the file
            Document doc = factory.newDocumentBuilder().parse(new File(filename));
            return doc;
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }	

    
	/**
	 * Effettua il parsing della porzione XML passata in ingresso, ed eventualmente effettua la validazione
	 * della porzione
	 * @param xmlContent contenuto xml di cui effettuare il parsing
	 * @param validating true se si vuole la validazione, false altrimenti
	 * @return Document ottenuto dal parsing del contenuto XML
	 */
    public static Document parseXmlContent(String xmlContent, boolean validating) {
        try {
            // Create a builder factory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(validating);
            
            // Create the builder and parse the file
            Document doc = factory.newDocumentBuilder().parse(new InputSource(new StringReader(xmlContent)));
            return doc;
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }	
    
    
}
