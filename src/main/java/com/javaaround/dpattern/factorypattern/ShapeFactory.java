package com.javaaround.dpattern.factorypattern;
public class ShapeFactory/* implements Factory*/ {
	
  /* @Override
   public void getColorFactory() {
      System.out.println("Inside Blue::fill() method.");
   }*/
      
   public static ShapeFactory newInstance() {
      return new ShapeFactory();
   }
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