package com.omarkhaled.paint.shape;

import java.util.List;

public class Line extends Shape{

    final String type = "Line";

    private List<Double> points;

    public String getType(){
        return this.type;
    }

    public List<Double> getPoints() {
        return points;
    }

    public void setPoints(List<Double> points) {
        this.points = points;
    }

    @Override
    public Shape Clone(){
        Line copy = new Line();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setPoints(this.getPoints());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setFill(this.getFill());
        copy.setRotation(this.getRotation());
        copy.setScaleX(this.getScaleX());
        copy.setScaleY(this.getScaleY());
        return copy;
    }
}
