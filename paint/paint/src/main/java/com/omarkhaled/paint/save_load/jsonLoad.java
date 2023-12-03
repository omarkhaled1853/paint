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
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class jsonLoad {

    private ShapeService ShapeService ;

    public jsonLoad(ShapeService ShapeService) {
        this.ShapeService = ShapeService;
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

            switch ((String) jo.get("type")){
                case "Circle":
                        Circle circle = (Circle) shape;
                        circle.setRadius(((BigDecimal) jo.get("radius")).doubleValue());
                    break;
                case "Elipse":
                    Elipse elipse = (Elipse) shape;
                    elipse.setRadiusX(((BigDecimal) jo.get("radiusX")).doubleValue());
                    elipse.setRadiusX(((BigDecimal) jo.get("radiusY")).doubleValue());
                    break;
                case "Triangle" :
                    Triangle triangle = (Triangle) shape;
                    triangle.setRadius(((BigDecimal) jo.get("radius")).doubleValue());
                    break;
                case "Quadrilateral" :
                    Quadrilateral quadrilateral = (Quadrilateral) shape;
                    quadrilateral.setWidth(((BigDecimal) jo.get("width")).doubleValue());
                    quadrilateral.setHeight(((BigDecimal) jo.get("height")).doubleValue());
                    break;
                case "Line" :
                    Line line = (Line) shape;
                    line.setX2(((BigDecimal) jo.get("x2")).doubleValue());
                    line.setY2(((BigDecimal) jo.get("y2")).doubleValue());
                    break;
                default:
                    break;
            }
        return shape;
    }

    //get default path
    private Path getDefaultPath(){
        String home = System.getProperty("user.home");
        return Paths.get(home).resolve("shapes.json");
    }

    //default load
    public void load(){
        load(getDefaultPath());
    }

    //custom load
    public void load(Path path){
        String jsonText = null;
        JsonArray ja = null;
        try {
            jsonText = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            ja = (JsonArray) Jsoner.deserialize(jsonText);
        } catch (JsonException e) {
            throw new RuntimeException(e);
        }
        List<Shape> list = new ArrayList<>();
        for(Object object : ja){
            JsonObject jo = (JsonObject) object;
            list.add(fromJsonObject(jo));
        }
        ShapeService.setList(list);
    }
}
