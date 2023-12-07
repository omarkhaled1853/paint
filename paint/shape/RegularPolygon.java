package com.omarkhaled.paint.shape;

public class RegularPolygon extends Shape {
    final String type = "RegularPolygon";
    private int sides;
    private double radius;

    public int getSides() {
        return sides;
    }

    public void setSides(int sides) {
        this.sides = sides;
    }

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
        RegularPolygon copy = new RegularPolygon();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setFill(this.getFill());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setIndex(this.getIndex());
        copy.setSides(this.getSides());
        copy.setRadius(this.getRadius());
        copy.setRotation(this.getRotation());
        copy.setScaleX(this.getScaleX());
        copy.setScaleY(this.getScaleY());
        copy.setIndex(this.getIndex());
        return copy;
    }
}
