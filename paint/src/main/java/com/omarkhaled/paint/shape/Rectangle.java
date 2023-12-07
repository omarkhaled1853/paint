package com.omarkhaled.paint.shape;

public class Rectangle extends Shape{
    final String type = "Rectangle";
    private double height;
    private double width;

    public String getType(){
        return this.type;
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
        copy.setRotation(this.getRotation());
        copy.setScaleX(this.getScaleX());
        copy.setScaleY(this.getScaleY());
        return copy;
    }

}
