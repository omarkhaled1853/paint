package com.omarkhaled.paint.shape;

public class Star extends Shape{
    final String type = "Star";
    private int numPoints;
    private double innerRadius;
    private double outerRadius;

    public int getNumPoints() {
        return numPoints;
    }

    public void setNumPoints(int numPoints) {
        this.numPoints = numPoints;
    }

    public Double getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(Double innerRadius) {
        this.innerRadius = innerRadius;
    }

    public Double getOuterRadius() {
        return outerRadius;
    }

    public void setOuterRadius(Double outerRadius) {
        this.outerRadius = outerRadius;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Shape Clone(){
        Star copy = new Star();
        copy.setX(this.getX());
        copy.setY(this.getY());
        copy.setFill(this.getFill());
        copy.setStroke(this.getStroke());
        copy.setStrokeWidth(this.getStrokeWidth());
        copy.setIndex(this.getIndex());
        copy.setNumPoints(this.getNumPoints());
        copy.setInnerRadius(this.getInnerRadius());
        copy.setOuterRadius(this.getOuterRadius());
        copy.setRotation(this.getRotation());
        copy.setScaleX(this.getScaleX());
        copy.setScaleY(this.getScaleY());
        copy.setIndex(this.getIndex());
        return copy;
    }
}
