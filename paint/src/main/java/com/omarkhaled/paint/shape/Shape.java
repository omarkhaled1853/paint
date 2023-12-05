package com.omarkhaled.paint.shape;

public abstract class Shape {
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
    public abstract Shape Clone();
    public abstract String getType();


}
