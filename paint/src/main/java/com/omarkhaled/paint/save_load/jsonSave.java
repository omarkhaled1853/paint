package com.omarkhaled.paint.save_load;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.omarkhaled.paint.shape.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class jsonSave {
    private final ShapeService shapeService;

    public jsonSave(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    //convert from object to json object
    private JsonObject toJsonObject(Shape shape){
        JsonObject jo = new JsonObject();
        jo.put("type", shape.getType());
        jo.put("index", shape.getIndex());
        jo.put("x", shape.getX());
        jo.put("y", shape.getY());
        jo.put("fill", shape.getFill());
        jo.put("stroke", shape.getStroke());
        jo.put("strokeWidth", shape.getStrokeWidth());
        switch (shape.getType()) {
            case "Circle" -> {
                Circle circle = (Circle) shape;
                jo.put("radius", circle.getRadius());
            }
            case "Ellipse" -> {
                Elipse elipse = (Elipse) shape;
                jo.put("radiusX", elipse.getRadiusX());
                jo.put("radiusY", elipse.getRadiusY());
            }
            case "Triangle" -> {
                Triangle triangle = (Triangle) shape;
                jo.put("radius", triangle.getRadius());
            }
            case "Square" -> {
                Square Square = (Square) shape;
                jo.put("width", Square.getWidth());
                jo.put("height", Square.getHeight());
            }
            case "Rectangle" -> {
                Rectangle Rectangle = (com.omarkhaled.paint.shape.Rectangle) shape;
                jo.put("width", Rectangle.getWidth());
                jo.put("height", Rectangle.getHeight());
            }
            case "Line" -> {
                Line line = (Line) shape;
                jo.put("points", line.getPoints());
            }
            case "Star" -> {
                Star star = (Star) shape;
                jo.put("innerRadius", star.getInnerRadius());
                jo.put("outerRadius", star.getOuterRadius());
                jo.put("numPoints", star.getNumPoints());
            }
            case "RegularPolygon" -> {
                RegularPolygon regularPolygon = (RegularPolygon) shape;
                jo.put("radius", regularPolygon.getRadius());
                jo.put("sides", regularPolygon.getSides());
            }
            default -> {
            }
        }
        return jo;
    }
    //custom save
    public void save(Path path){
        JsonArray ja = new JsonArray();

        for (int i = 0; i < shapeService.getList().size(); i++){
            ja.add(toJsonObject(shapeService.getList().get(i)));
        }

        String jsonText = Jsoner.serialize(ja);
        try {
            Files.write(path, jsonText.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            System.out.println("shapes not saved");
//            throw new RuntimeException(e);
        }
    }
}
