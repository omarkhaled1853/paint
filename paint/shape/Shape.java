package com.omarkhaled.paint.shape;


import jakarta.xml.bind.annotation.XmlElement;

public abstract class Shape {
    private double x;
    private double y;
    private String fill;
    private String stroke;
    private double strokeWidth;

    private double rotation;

    private double scaleX;

    private double scaleY;

    private String id;

    @XmlElement
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

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getScaleX() {
        return scaleX;
    }

    public void setScaleX(double scaleX) {
        this.scaleX = scaleX;
    }

    public double getScaleY() {
        return scaleY;
    }

    public void setScaleY(double scaleY) {
        this.scaleY = scaleY;
    }

    public String getIndex() {
        return id;
    }

    public void setIndex(String index) {
        this.id = id;
    }
    public abstract Shape Clone();
    public abstract String getType();


}
