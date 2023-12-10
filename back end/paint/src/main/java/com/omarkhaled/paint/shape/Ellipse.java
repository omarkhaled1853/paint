package com.omarkhaled.paint.shape;

public class Ellipse extends Shape{
    final String type = "Ellipse";
    private double radiusX;
    private double radiusY;
    public String getType(){
        return this.type;
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
        copy.setRotation(this.getRotation());
        copy.setScaleX(this.getScaleX());
        copy.setScaleY(this.getScaleY());
        copy.setId(this.getId());
        return copy;
    }
}
