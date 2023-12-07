package com.omarkhaled.paint.shape;

public class Triangle extends Shape{

    final String type = "Triangle";
    private double radius;

    public String getType(){
        return this.type;
    }
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
        copy.setRotation(this.getRotation());
        copy.setScaleX(this.getScaleX());
        copy.setScaleY(this.getScaleY());
        copy.setIndex(this.getIndex());
        return copy;
    }
}
