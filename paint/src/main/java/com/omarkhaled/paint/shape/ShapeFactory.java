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
            case "Square" -> {
                return new Square();
            }
            case "Rectangle" -> {
                return new Rectangle();
            }
            case "Star" -> {
                return new Star();
            }
            case "RegularPolygon" -> {
                return new RegularPolygon();
            }
            default -> throw new RuntimeException();
        }
    }
}
