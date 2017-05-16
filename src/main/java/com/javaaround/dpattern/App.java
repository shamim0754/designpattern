package com.javaaround.dpattern;
import com.javaaround.dpattern.factorypattern.FactoryFinder;
import com.javaaround.dpattern.factorypattern.AbstractFactory;
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

       //usage of abstract factory pattern 
        AbstractFactory  colorFactory = FactoryFinder.getFactory("COLOR");
        Color color = colorFactory.getColor("RED");
        color.fill(); 


    }
}
