package com.omarkhaled.paint.shape;

public class Elipse extends Shape{
    final String type = "Elipse";
    private double radiusX;
    private double radiusY;
    public String getType(){
        return this.type;
    }
    @Override
    public Shape Clone(){
        Elipse copy = new Elipse();
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
