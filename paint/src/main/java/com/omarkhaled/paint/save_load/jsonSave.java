package com.omarkhaled.paint.save_load;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import com.omarkhaled.paint.shape.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class jsonSave {
    private ShapeService shapeService;

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
        switch (shape.getType()){
            case "Circle":
                Circle circle = (Circle) shape;
                jo.put("radius", circle.getRadius());
                break;
            case "Ellipse":
                Elipse elipse = (Elipse) shape;
                jo.put("radiusX", elipse.getRadiusX());
                jo.put("radiusY", elipse.getRadiusY());
                break;
            case "Triangle" :
                Triangle triangle = (Triangle) shape;
                jo.put("radius", triangle.getRadius());
                break;
            case "Quadrilateral" :
                Quadrilateral quadrilateral = (Quadrilateral) shape;
                jo.put("width", quadrilateral.getWidth());
                jo.put("height", quadrilateral.getHeight());
                break;
            case "Line" :
                Line line = (Line) shape;
                jo.put("points", line.getPoints());
                break;
            default:
                break;
        }
        return jo;
    }

    //default path
//    private Path getDefaultPath(){
//        String home = System.getProperty("user.home");
//        return Paths.get(home).resolve("shapes.json");
//    }

    //default save
//    public void save(){
//        save(getDefaultPath());
//    }


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
            throw new RuntimeException(e);
        }
    }
}
