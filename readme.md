##  Design pattern ##

 Design patterns are standard solutions/technique to general problems that software developers faced during software development.

## Gang of Four (GOF) ##

 Erich Gamma, Richard Helm, Ralph Johnson and John Vlissides published a book titled Design Patterns - Elements of Reusable Object-Oriented Software which initiated the concept of Design Pattern in Software development.In this book,there are 23 design patterns which can be classified in three categories:
 1. <b>Creational</b>:
 These design patterns provide a way to create objects while hiding the creation logic, rather than instantiating objects directly using new opreator. This gives program more flexibility in deciding which objects need to be created for a given use case
 	1. Factory Method pattern 
 	2. Abstract Factory pattern 
 	3. Singleton pattern 
 	4. Prototype Pattern 
 	5. Builder pattern 
 	6. Object Pool Pattern 
 2. <b>Structural</b>:
 These design patterns concern class and object composition. Concept of inheritance is used to compose interfaces and define ways to compose objects to obtain new functionalities.
 	1. Adapter pattern 
 	2. Bridge pattern 
 	3. Filter pattern 
 	4. Composite Pattern 
 	5. Decorator pattern 
 	6. Facade Pattern  
 	7. Flyweight Pattern  
 	8. proxy Pattern  
 3. <b>Behavioral pattern</b>:
 These design patterns are specifically concerned with communication between objects.
 	1. chain of responsibility pattern 
 	2. Command pattern 
 	3. Interpreter pattern 
 	4. Iterator Pattern 
 	5. Mediator pattern
 	6. Observer pattern
 	7. State pattern
 	8. Null Object pattern
 	9. Strategy pattern

## Factory Method pattern ## 

Factory Method pattern says that define an factory(interface or abstract class) for creating an object	without exposing the creation logic to the App client.

Step 1 : <br>
Create an interface or abstract class
```java
package com.javaaround.dpattern.factorypattern;
public interface Shape {
   void draw();
}
```

Step 2 : <br>
Create implementation classes implementing the same interface.

```java
package com.javaaround.dpattern.factorypattern;
public class Square implements Shape {

   @Override
   public void draw() {
      System.out.println("This is square draw() method.");
   }
}
```

```java
package com.javaaround.dpattern.factorypattern;
public class Rectangle implements Shape {

   @Override
   public void draw() {
      System.out.println("This is Rectangle method.");
   }
}
```

```java
package com.javaaround.dpattern.factorypattern;
public class Circle implements Shape {

   @Override
   public void draw() {
      System.out.println("This is Circle method");
   }
}
```


Step 3 : <br>
Create public Factory method to get instance based on param value

```java
package com.javaaround.dpattern.factorypattern;
public class ShapeFactory {
	
   //use getShape method to get object of type shape 
   public Shape getShape(String shapeType){
   	  if(shapeType == null)
         return null;	
      if(shapeType.equalsIgnoreCase("CIRCLE"))
         return new Circle();
         
      else if(shapeType.equalsIgnoreCase("RECTANGLE"))
         return new Rectangle();
         
      else if(shapeType.equalsIgnoreCase("SQUARE"))
         return new Square();
      
      
      return null;
   }
}   
```
Usage : <br>
```java
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

```