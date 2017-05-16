package com.javaaround.dpattern.factorypattern;
public abstract class AbstractFactory {
   abstract public Shape getShape(String shape);
   abstract public Color getColor(String color);
}