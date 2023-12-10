package com.omarkhaled.paint.shape;

public class Polygon extends Shape {
    final String type = "Polygon";
    private double radius;

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Shape Clone() {
        Polygon copy = new Polygon();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setFill(this.getFill());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setRadius(this.getRadius());
        copy.setRotation(this.getRotation());
        copy.setScaleX(this.getScaleX());
        copy.setScaleY(this.getScaleY());
        copy.setId(this.getId());
        return copy;
    }
}
