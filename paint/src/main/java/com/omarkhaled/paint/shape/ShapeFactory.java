package com.omarkhaled.paint.shape;

public class ShapeFactory {
    public Shape createShape(String type){
        if(type == null)
            return null;
        switch (type){
            case "Line" -> {
                return new Line();
            }
            case "Circle" -> {
                return new Circle();
            }
            case "Ellipse" -> {
                return new Elipse();
            }
            case "Triangle" -> {
                return new Triangle();
            }
            case "Square", "Rectangle" -> {
                return new Quadrilateral();
            }
            default -> throw new RuntimeException();
        }
    }
}