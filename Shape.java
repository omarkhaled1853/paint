public interface Shape{

    Shape clone();
}
abstract class MainProperties implements Shape{
    private double x;
    private double y;
    private String fill;
    private String stroke;
    private double strokeWidth;

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getFill() {
        return fill;
    }

    public void setFill(String fill) {
        this.fill = fill;
    }

    public String getStroke() {
        return stroke;
    }

    public void setStroke(String stroke) {
        this.stroke = stroke;
    }

    public double getStrokeWidth() {
        return strokeWidth;
    }

    public void setStrokeWidth(double strokeWidth) {
        this.strokeWidth = strokeWidth;
    }
}

class Line extends MainProperties{

    private double rotation;
    @Override
    public Shape clone(){
        Line copy = new Line();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setFill(this.getFill());
        copy.setRotation(this.getRotation());
        return copy;
    }
    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
}
class Circle extends MainProperties{
    private double radius;
    @Override
    public Shape clone(){
        Circle copy = new Circle();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setFill(this.getFill());
        copy.setRadius(this.getRadius());
        return copy;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
class Ellipse extends MainProperties{
    private double radiusX;
    private double radiusY;
    @Override
    public Shape clone(){
        Ellipse copy = new Ellipse();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setFill(this.getFill());
        copy.setRadiusX(this.getRadiusX());
        copy.setRadiusY(this.getRadiusY());
        return copy;
    }

    public double getRadiusX() {
        return radiusX;
    }

    public void setRadiusX(double radiusX) {
        this.radiusX = radiusX;
    }

    public double getRadiusY() {
        return radiusY;
    }

    public void setRadiusY(double radiusY) {
        this.radiusY = radiusY;
    }
}
class Triangle extends MainProperties{
    private double rotation;

    @Override
    public Shape clone(){
        Triangle copy = new Triangle();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setFill(this.getFill());
        copy.setRotation(this.getRotation());
        return copy;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }
}
class Rectangle extends MainProperties{
    private double height;
    private double width;
    private double rotation;
    private double cornerRadius;

    @Override
    public Shape clone() {
        Rectangle copy = new Rectangle();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setFill(this.getFill());
        copy.setHeight(this.getHeight());
        copy.setWidth(this.getWidth());
        copy.setRotation(this.getRotation());
        copy.setCornerRadius(this.getCornerRadius());
        return copy;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getCornerRadius() {
        return cornerRadius;
    }

    public void setCornerRadius(double cornerRadius) {
        this.cornerRadius = cornerRadius;
    }
}
class ShapeFactory{
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
                return new Ellipse();
            }
            case "Triangle" -> {
                return new Triangle();
            }
            case "Rectangle", "Square" -> {
                return new Rectangle();
            }
            default -> throw new RuntimeException();
        }
    }
}


