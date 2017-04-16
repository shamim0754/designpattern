package com.javaaround.dpattern.factorypattern;
public interface Factory {
   abstract ColorFactory getColorFactory(String color);
   abstract ShapeFactory getShapeFactory(String shape) ;
}