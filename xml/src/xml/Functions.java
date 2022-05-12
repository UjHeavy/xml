package xml;

import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Functions {

    public static void addElement(List<data> stock) throws ParserConfigurationException {
        Scanner scn = new Scanner(System.in);
        Integer id = stock.size();
        
        System.out.println("Új elem adatai: ");
        System.out.print("Termék neve: ");
        String name = scn.nextLine();
        System.out.print("Termék ára: ");
        Integer price = Integer.parseInt(scn.nextLine());
        System.out.print("Elerhetö-e a termék? (i/n): ");
        String nextLine = scn.nextLine();

        data tmp = new data(id, name, price, nextLine.equals("i"));
        stock.add(tmp);
        ToXml.writeFile(stock);
    }

    public static void editElement(List<data> stock) throws ParserConfigurationException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Szerkesztendö elem id-je: ");
        Integer searchId = Integer.parseInt(scn.nextLine());
        System.out.println("Jelenlegi adatok: " );
        System.out.println("Termék neve: "+ stock.get(searchId).getName());
        System.out.println("Termék ára: "+ stock.get(searchId).getPrice());
        System.out.println("Elerhetö-e a termék?: "+ stock.get(searchId).getInStorage());
        
        System.out.println("Új adatok: ");
        System.out.println("Termék neve: ");
        String name = scn.nextLine();
        System.out.println("Termék ára: ");
        Integer price = Integer.parseInt(scn.nextLine());
        System.out.println("Elerhetö-e a termék? (i/n): ");
        String nextLine = scn.nextLine();

        data tmp = new data(name, price, nextLine.equals("i"));
        stock.set(searchId, tmp);
        ToXml.writeFile(stock);
    }

    public static void removeElement(List<data> stock) throws ParserConfigurationException {
        Scanner scn = new Scanner(System.in);
        System.out.println("Törlendö elem id-je: ");
        Integer searchId = Integer.parseInt(scn.nextLine());
        stock.remove(searchId);
        ToXml.writeFile(stock);
    }

    public static void searchById(NodeList nodeList, String searchId) {
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tmpNode = nodeList.item(count);

            if (tmpNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) tmpNode;
                if (searchId.equals(element.getElementsByTagName("id").item(count).getTextContent())) {
                    Integer id = Integer.valueOf(element.getElementsByTagName("id").item(count).getTextContent());
                    String name = element.getElementsByTagName("name").item(count).getTextContent();
                    Integer price = Integer.valueOf(element.getElementsByTagName("price").item(count).getTextContent());
                    Boolean supply = Boolean.valueOf(element.getElementsByTagName("supply").item(count).getTextContent());
                    data tmp = new data(id, name, price, supply);

                    System.out.println("Product id: " + tmp.getId());
                    System.out.println("Product name:" + tmp.getName());
                    System.out.println("Product price:" + tmp.getPrice());
                    System.out.println("Product is in supply:" + tmp.getInStorage().toString());
                }
            }
        }
    }

    public static void searchByName(NodeList nodeList, String searchName) {
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tmpNode = nodeList.item(count);

            if (tmpNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) tmpNode;
                if (searchName.equals(element.getElementsByTagName("name").item(count).getTextContent())) {
                    Integer id = Integer.valueOf(element.getElementsByTagName("id").item(count).getTextContent());
                    String name = element.getElementsByTagName("name").item(count).getTextContent();
                    Integer price = Integer.valueOf(element.getElementsByTagName("price").item(count).getTextContent());
                    Boolean supply = Boolean.valueOf(element.getElementsByTagName("supply").item(count).getTextContent());
                    data tmp = new data(id, name, price, supply);

                    System.out.println("Product id: " + tmp.getId());
                    System.out.println("Product name:" + tmp.getName());
                    System.out.println("Product price:" + tmp.getPrice());
                    System.out.println("Product is in supply:" + tmp.getInStorage().toString());
                }
            }
        }
    }
}
