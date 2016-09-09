package com.javaaround.dpattern;
import com.javaaround.dpattern.factorypattern.ShapeFactory;
import com.javaaround.dpattern.factorypattern.Shape;

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
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getShape("CIRCLE");
        shape.draw(); 

    }
}
