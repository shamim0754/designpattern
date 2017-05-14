package com.javaaround.dpattern;
import com.javaaround.dpattern.factorypattern.ShapeFactory;
import com.javaaround.dpattern.factorypattern.Shape;
import com.javaaround.dpattern.factorypattern.ColorFactory;
import com.javaaround.dpattern.factorypattern.Color;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	System.out.println("Hello World");

    	//usage of factory pattern 
       // ShapeFactory shapeFactory = ShapeFactory.newInstance();
        Shape shape = ShapeFactory.getShape("CIRCLE");
        shape.draw(); 
        System.out.println(System.getProperty("user.dir"));
        try {
            File fXmlFile = new File(System.getProperty("user.dir")+"/src/main/java/com/javaaround/dpattern//factorypattern/staff.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            //get staff node list
            NodeList nList = doc.getElementsByTagName("staff");

            //iterate node
            for (int temp = 0; temp < nList.getLength(); temp++) {
                //get each node
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    //display content
                    System.out.println("Staff id : " + eElement.getAttribute("id"));
                    System.out.println("First Name : " + eElement.getElementsByTagName("firstname").item(0).getTextContent());
                    System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                    System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                    System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());

                }
            }
        }    
        catch (Exception e) {
             e.printStackTrace();
        }
       /* //usage of abstract factory pattern 
        ColorFactory colorFactory = AbstractFactory.getFactory("COLOR");
        Color color = colorFactory.getColor("RED");
        color.fill(); */


    }
}
