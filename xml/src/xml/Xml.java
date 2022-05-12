package xml;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import static xml.ToXml.xmlToInputStream;

public class Xml {
    
    public static void main(String[] args) throws ParserConfigurationException, Exception {
        //Abandon all hope, ye who enter here
        List<data> stock = ToXml.getFile();
        Scanner scn= new Scanner(System.in);
        System.out.println("Mit akar csinálni?");
        System.out.println("Keresés(k), elem törlése(t), elem hozzáadása(h), elem szerkesztése(s)");
        String answer = scn.nextLine();
        Boolean k=false,t=false,h=false,s=false;
        switch (answer) {
            case "k": k = true; break;
            case "t": t = true; break;
            case "h": h = true; break;
            case "s": s = true; break;
            default:break;
        }
        
        DocumentBuilderFactory dbfRead = DocumentBuilderFactory.newInstance();
        DocumentBuilder BuilderRead = dbfRead.newDocumentBuilder();
        InputStream is = xmlToInputStream("stock.xml");
        Document Read = BuilderRead.parse(is);
        
        if(k){
            System.out.print("Név vagy id szerinti keresés? (name/id): " );
            String search= scn.nextLine();
            if(search.equals("name")){
                System.out.print("A keresett név: ");
                search= scn.nextLine();
                Functions.searchByName(Read.getChildNodes(),search);
            }
            else {
                System.out.print("A keresett id: ");
                search= scn.nextLine();
                Functions.searchById(Read.getChildNodes(),search);
            }
        }
        else if(t){
            Functions.removeElement(stock);
        }
        else if(h){
            Functions.addElement(stock);
        }
        else if(s){
            Functions.editElement(stock);
        }
    }
}
