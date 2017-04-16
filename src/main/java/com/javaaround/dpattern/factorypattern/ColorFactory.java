package com.javaaround.dpattern.factorypattern;
public class ColorFactory {
	
   public Color getColor(String color) {
   
      if(color == null)
         return null;
      else if(color.equalsIgnoreCase("RED"))
         return new Red();
         
      else if(color.equalsIgnoreCase("GREEN"))
         return new Green();
         
      else if(color.equalsIgnoreCase("BLUE"))
         return new Blue();
      
      
      return null;
   }
}