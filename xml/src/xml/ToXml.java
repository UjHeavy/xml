package xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import static java.lang.Integer.parseInt;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ToXml {

    public static List<data> getFile() {
        List<data> stock = new ArrayList();

        try {
            File xmlDoc = new File("stock.xml");

            DocumentBuilderFactory Factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder Builder = Factory.newDocumentBuilder();
            Document doc = Builder.parse(xmlDoc);
            NodeList nList = doc.getElementsByTagName("product");
            System.out.println(nList.getLength());
            for (int i = 0; i < nList.getLength(); i++) {
                
                Node nNode = nList.item(i);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nNode;

                    Integer id = parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    Integer price = parseInt(element.getElementsByTagName("price").item(0).getTextContent());
                    Boolean supply = Boolean.valueOf(element.getElementsByTagName("supply").item(0).getTextContent());

                    data tmp = new data(id, name, price, supply);
                    stock.add(i,tmp);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stock;
    }

    public static void writeFile(List<data> stock) throws ParserConfigurationException {
        DocumentBuilderFactory Factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder Builder = Factory.newDocumentBuilder();
        Document doc = Builder.newDocument();
        Element root = doc.createElement("storage");
        doc.appendChild(root);
        for (data i : stock) {

            Element prod = doc.createElement("product");
            root.appendChild(prod);

            Element id = doc.createElement("id");
            id.setTextContent(i.getId().toString());
            prod.appendChild(id);

            Element name = doc.createElement("name");
            name.setTextContent(i.getName());
            prod.appendChild(name);

            Element price = doc.createElement("price");
            price.setTextContent(i.getPrice().toString());
            prod.appendChild(price);

            Element supply = doc.createElement("supply");
            supply.setTextContent(i.getInStorage().toString());
            prod.appendChild(supply);
        }
        
        try (FileOutputStream output = new FileOutputStream("stock.xml")) {
            createFile(doc, output);
            System.out.println("Fájl kiírva");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void createFile(Document file, OutputStream output) throws TransformerException {
        TransformerFactory Factory = TransformerFactory.newInstance();
        Transformer transform = Factory.newTransformer();
        DOMSource source = new DOMSource(file);
        StreamResult result = new StreamResult(output);

        transform.transform(source, result);
    }


    public static InputStream xmlToInputStream(final String fileName) throws Exception {
        File initialFile = new File(fileName);
        InputStream targetStream = new FileInputStream(initialFile);
        return targetStream;
    }

}
