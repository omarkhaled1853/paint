package com.omarkhaled.paint.shape;

public class Line extends Shape{

    final String type = "Line";

    private double x2;
    private double y2;

    public String getType(){
        return this.type;
    }
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
        return copy;
    }
}
