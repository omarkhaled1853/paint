public abstract class Shape {
    abstract Shape Clone();
    abstract String getType();
    private double x;
    private double y;
    private String fill;
    private String stroke;
    private double strokeWidth;
    private long index;
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
    public Long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

}

class Line extends Shape{

    final String type = "Line";
    String getType(){
        return this.type;
    }
    private double x2;
    private double y2;

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    @Override
    public Shape Clone(){
        Line copy = new Line();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setX2(this.getX2());
        copy.setY2(this.getY2());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setFill(this.getFill());
        copy.setIndex(this.getIndex());
        return copy;
    }
}
class Circle extends Shape{
    String getType(){
        return this.type;
    }
    final String type = "Circle";
    private double radius;
    @Override
    public Shape Clone(){
        Circle copy = new Circle();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setFill(this.getFill());
        copy.setRadius(this.getRadius());
        copy.setIndex(this.getIndex());
        return copy;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
class Ellipse extends Shape{
    String getType(){
        return this.type;
    }
    final String type = "Ellipse";
    private double radiusX;
    private double radiusY;
    @Override
    public Shape Clone(){
        Ellipse copy = new Ellipse();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setFill(this.getFill());
        copy.setRadiusX(this.getRadiusX());
        copy.setRadiusY(this.getRadiusY());
        copy.setIndex(this.getIndex());
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
class Triangle extends Shape{
    String getType(){
        return this.type;
    }
    final String type = "Triangle";
    private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public Shape Clone(){
        Triangle copy = new Triangle();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setFill(this.getFill());
        copy.setRadius(this.getRadius());
        copy.setIndex(this.getIndex());
        return copy;
    }


}
class Rectangle extends Shape{
    String getType(){
        return this.type;
    }
    final String type = "Rectangle";
    private double height;
    private double width;

    @Override
    public Shape Clone() {
        Rectangle copy = new Rectangle();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setFill(this.getFill());
        copy.setHeight(this.getHeight());
        copy.setWidth(this.getWidth());
        copy.setIndex(this.getIndex());
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

}
class Square extends Shape{
    String getType(){
        return this.type;
    }
    final String type = "Square";
    private double radius;
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public Shape Clone() {
        Square copy = new Square();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setFill(this.getFill());
        copy.setRadius(this.getRadius());
        copy.setIndex(this.getIndex());
        return copy;
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
            case "Rectangle"-> {
                return new Rectangle();
            }
            case "Square" -> {
                return new Square();
            }
            default -> throw new RuntimeException();
        }
    }
}
