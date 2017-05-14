##  Design pattern ##

 Design patterns are standard solutions/technique for commonly (and frequently) occurred problems that software developers faced during software development.

## Gang of Four (GOF) ##

 Erich Gamma, Richard Helm, Ralph Johnson and John Vlissides published a book titled Design Patterns - Elements of Reusable Object-Oriented Software which initiated the concept of Design Pattern in Software development.In this book,there are 23 design patterns which can be classified in three categories:
 1. <b>Creational</b>:
 If object creation code(using new opreator - traditional way to create object) is spread in whole application, and if you need to change the process of object creation e.g new constructor is used then you need to go in each and every place to make necessary changes . As a result application manages become more difficult and complicated,need to more testing effort.
 Following design pattern gives application more flexibility and smart way to create object
 	1. Factory(Factory Method) pattern 
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

Factory, as name suggest, is a place to create some different products which are somehow similar in features yet divided in categories.
Programatically, factory pattern have creational methods that return instances of different classes of abstract/interface type.

## Use case ##

Factory pattern is most suitable where there is some complex object creation steps are involved and may be changed further.

## Use at jdk ###
1. java.util.Calendar#getInstance()
2. java.util.ResourceBundle#getBundle()
3. java.util.EnumSet#of()
4. javax.xml.bind.JAXBContext#createMarshaller()
5. java.lang.Class#forName()

```java
public abstract class Calendar implements Serializable, Cloneable, Comparable<Calendar> {
    /**
      * Gets a calendar using the default time zone and locale. The
      * <code>Calendar</code> returned is based on the current time
      * in the default time zone with the default locale.
      *
      * @return a Calendar.
      */
     public static Calendar getInstance()
     {
         Calendar cal = createCalendar(TimeZone.getDefaultRef(), Locale.getDefault(Locale.Category.FORMAT));
         cal.sharedZone = true;           
          return cal;
     }

     private static Calendar createCalendar(TimeZone zone,
                                              Locale aLocale)
        {
            Calendar cal = null;
  
            String caltype = aLocale.getUnicodeLocaleType("ca");
            if (caltype == null) {
                // Calendar type is not specified.
                // If the specified locale is a Thai locale,
                // returns a BuddhistCalendar instance.
                if ("th".equals(aLocale.getLanguage())
                        && ("TH".equals(aLocale.getCountry()))) {
                    cal = new BuddhistCalendar(zone, aLocale);
                } else {
                    cal = new GregorianCalendar(zone, aLocale);
                }
            } else if (caltype.equals("japanese")) {
                cal = new JapaneseImperialCalendar(zone, aLocale);
            } else if (caltype.equals("buddhist")) {
                cal = new BuddhistCalendar(zone, aLocale);
            } else {
                // Unsupported calendar type.
                // Use Gregorian calendar as a fallback.
                cal = new GregorianCalendar(zone, aLocale);
            }
    
            return cal;
       }
}
```
### Demo on Factory pattern ###
![Image of Nested](images/factory_pattern_uml_diagram.png) 

Create an interface or abstract class
```java
package com.javaaround.dpattern.factorypattern;
public interface Shape {
   void draw();
}
```



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



Create Factory class to get instance of above class

```java
package com.javaaround.dpattern.factorypattern;
public class ShapeFactory {
	
 
   //use getShape method to get object of type shape 
   public static Shape getShape(String shapeType){
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
    	
      //get shape by providing param
      Shape shape = ShapeFactory.getShape("CIRCLE");
      //display
      shape.draw(); 

    }
}

```

## Abstract Factory pattern ## 
Provide an interface for creating families of related or dependent objects without specifying their concrete classes. A creational methods returning the factory itself which in turn can be used to create another abstract/interface type
It is considered as another layer of abstraction over factory pattern

### JDK Usage ###
1. javax.xml.parsers.DocumentBuilderFactory#newInstance()
2. javax.xml.transform.TransformerFactory#newInstance()
3. javax.xml.xpath.XPathFactory#newInstance()

### DocumentBuilderFactory ###

1. create staff.xml at factorypattern

```xml
<?xml version="1.0"?>
<company>
  <staff id="1001">
    <firstname>shamim</firstname>
    <lastname>miah</lastname>
    <nickname>shamim</nickname>
    <salary>100000</salary>
  </staff>
  <staff id="2001">
    <firstname>alamin</firstname>
    <lastname>miah</lastname>
    <nickname>arif</nickname>
    <salary>200000</salary>
  </staff>
</company>
```
2. Update App.java

```java
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
```

### Source code of DocumentBuilderFactory ###

```java
 public static DocumentBuilderFactory More ...newInstance() {
    try {
        return (DocumentBuilderFactory) FactoryFinder.find(
            /* The default property name according to the JAXP spec */
            "javax.xml.parsers.DocumentBuilderFactory",
           /* The fallback implementation class name */
           "com.sun.org.apache.xerces.internal.jaxp.DocumentBuilderFactoryImpl");
   } catch (FactoryFinder.ConfigurationError e) {
        throw new FactoryConfigurationError(e.getException(),
                                           e.getMessage());
   }
}
```

FactoryFinder.java

```java
static Object newInstance(String className, ClassLoader cl,
                                           boolean doFallback)
              throws ConfigurationError
{
   // assert(className != null);

   try {
      Class providerClass;
       if (cl == null) {
           // If classloader is null Use the bootstrap ClassLoader.  
           // Thus Class.forName(String) will use the current
           // ClassLoader which will be the bootstrap ClassLoader.
           providerClass = Class.forName(className);
       } else {
           try {
               providerClass = cl.loadClass(className);
           } catch (ClassNotFoundException x) {
             if (doFallback) {
                   // Fall back to current classloader
                   cl = FactoryFinder.class.getClassLoader();
                  if (cl != null) {
                       providerClass = cl.loadClass(className);
                   }
                   else {
                       providerClass = Class.forName(className);
                   }
               } else {
                   throw x;
               }
           }
       }
                   
       Object instance = providerClass.newInstance();
       if (debug) dPrint("created new instance of " + providerClass +
              " using ClassLoader: " + cl);
       return instance;
   } catch (ClassNotFoundException x) {
       throw new ConfigurationError(
           "Provider " + className + " not found", x);
  } catch (Exception x) {
       throw new ConfigurationError(
          "Provider " + className + " could not be instantiated: " + x,
           x);
   }
}

/**
* Finds the implementation Class object in the specified order.  Main
* entry point.
* @return Class object of factory, never null
*
* @param factoryId             Name of the factory to find, same as
*                              a property name
* @param fallbackClassName     Implementation class name, if nothing else
 *                              is found.  Use null to mean no fallback.
*
* Package private so this code can be shared.
*/
static Object find(String factoryId, String fallbackClassName)
   throws ConfigurationError
{        

   // Figure out which ClassLoader to use for loading the provider
   // class.  If there is a Context ClassLoader then use it.
   
  ClassLoader classLoader = SecuritySupport.getContextClassLoader();
  
   if (classLoader == null) {
       // if we have no Context ClassLoader
       // so use the current ClassLoader
       classLoader = FactoryFinder.class.getClassLoader();
   }

   if (debug) dPrint("find factoryId =" + factoryId);
   
   // Use the system property first
   try {
       String systemProp = SecuritySupport.getSystemProperty(factoryId);
       if (systemProp != null && systemProp.length() > 0) {
          if (debug) dPrint("found system property, value=" + systemProp);
         return newInstance(systemProp, classLoader, true);
    }
   } catch (SecurityException se) {
       //if first option fails due to any reason we should try next option in the
       //look up algorithm.
   }

   // try to read from $java.home/lib/jaxp.properties
   try {
       String javah = SecuritySupport.getSystemProperty("java.home");
       String configFile = javah + File.separator +
           "lib" + File.separator + "jaxp.properties";
       String factoryClassName = null;
       if(firstTime){
           synchronized(cacheProps){
               if(firstTime){
                   File f=new File( configFile );
                   firstTime = false;
                   if(SecuritySupport.doesFileExist(f)){
                       if (debug) dPrint("Read properties file "+f);
                       //cacheProps.load( new FileInputStream(f));
                       cacheProps.load(SecuritySupport.getFileInputStream(f));
                   }
               }
           }
       }
       factoryClassName = cacheProps.getProperty(factoryId);            

       if(factoryClassName != null){
           if (debug) dPrint("found in $java.home/jaxp.properties, value=" + factoryClassName);
           return newInstance(factoryClassName, classLoader, true);
       }
   } catch(Exception ex ) {
       if( debug ) ex.printStackTrace();
   }

   // Try Jar Service Provider Mechanism
   Object provider = findJarServiceProvider(factoryId);
   if (provider != null) {
       return provider;
   }
   if (fallbackClassName == null) {
     throw new ConfigurationError(
         "Provider for " + factoryId + " cannot be found", null);
   }

   if (debug) dPrint("loaded from fallback value: " + fallbackClassName);
   return newInstance(fallbackClassName, classLoader, true);
}       
```