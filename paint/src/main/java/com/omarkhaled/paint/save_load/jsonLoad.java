package com.omarkhaled.paint.save_load;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.omarkhaled.paint.shape.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class jsonLoad {

    private final ShapeService shapeService ;

    public jsonLoad(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    //convert from json object to object
        private Shape fromJsonObject(JsonObject jo){
            ShapeFactory shapeFactory = new ShapeFactory();
            Shape shape = shapeFactory.createShape((String) jo.get("type"));
            shape.setIndex(((BigDecimal) jo.get("index")).longValue());
            shape.setX(((BigDecimal) jo.get("x")).doubleValue());
            shape.setY(((BigDecimal) jo.get("y")).doubleValue());
            shape.setFill((String) jo.get("fill"));
            shape.setStroke((String) jo.get("stroke"));
            shape.setStrokeWidth(((BigDecimal) jo.get("strokeWidth")).doubleValue());

            switch ((String) jo.get("type")) {
                case "Circle" -> {
                    Circle circle = (Circle) shape;
                    circle.setRadius(((BigDecimal) jo.get("radius")).doubleValue());
                }
                case "Ellipse" -> {
                    Elipse elipse = (Elipse) shape;
                    elipse.setRadiusX(((BigDecimal) jo.get("radiusX")).doubleValue());
                    elipse.setRadiusY(((BigDecimal) jo.get("radiusY")).doubleValue());
                }
                case "Triangle" -> {
                    Triangle triangle = (Triangle) shape;
                    triangle.setRadius(((BigDecimal) jo.get("radius")).doubleValue());
                }
                case "Rectangle" -> {
                    Rectangle Rectangle = (com.omarkhaled.paint.shape.Rectangle) shape;
                    Rectangle.setWidth(((BigDecimal) jo.get("width")).doubleValue());
                    Rectangle.setHeight(((BigDecimal) jo.get("height")).doubleValue());
                }
                case "Square" -> {
                    Square Square = (Square) shape;
                    Square.setWidth(((BigDecimal) jo.get("width")).doubleValue());
                    Square.setHeight(((BigDecimal) jo.get("height")).doubleValue());
                }
                case "Line" -> {
                    Line line = (Line) shape;
                    line.setPoints((List<Double>) jo.get("points"));
                }
                case "Star" -> {
                    Star star = (Star) shape;
                    star.setInnerRadius(((BigDecimal) jo.get("innerRadius")).doubleValue());
                    star.setOuterRadius(((BigDecimal) jo.get("outerRadius")).doubleValue());
                    star.setNumPoints(((BigDecimal) jo.get("numPoints")).intValue());
                }
                case "RegularPolygon" -> {
                    RegularPolygon regularPolygon = (RegularPolygon) shape;
                    regularPolygon.setRadius(((BigDecimal) jo.get("radius")).doubleValue());
                    regularPolygon.setSides(((BigDecimal) jo.get("sides")).intValue());
                }
                default -> {
                }
            }
        return shape;
    }

    //custom load
    public void load(Path path){
        String jsonText;
        JsonArray ja;
        try {
            jsonText = new String(Files.readAllBytes(path));
            ja = (JsonArray) Jsoner.deserialize(jsonText);
            List<Shape> list = new ArrayList<>();
            for(Object object : ja){
                JsonObject jo = (JsonObject) object;
                list.add(fromJsonObject(jo));
            }
            shapeService.setList(list);
            shapeService.setGeneralIndex(0L);
            shapeService.setUndo(new Stack<>());
            shapeService.setRedo(new Stack<>());
            shapeService.setShapes(new HashMap<>());
            shapeService.modifyIndex();
            shapeService.setMapFromList();
        } catch (IOException | JsonException e) {
            shapeService.setList(new ArrayList<>());
            shapeService.setGeneralIndex(0L);
            shapeService.setUndo(new Stack<>());
            shapeService.setRedo(new Stack<>());
            shapeService.setShapes(new HashMap<>());
            shapeService.modifyIndex();
            shapeService.setMapFromList();
//            throw new RuntimeException(e);
        }

    }
}
