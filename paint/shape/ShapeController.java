package com.omarkhaled.paint.shape;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.omarkhaled.paint.save_load.jsonLoad;
import com.omarkhaled.paint.save_load.jsonSave;
import com.omarkhaled.paint.save_load.xmlLoad;
import com.omarkhaled.paint.save_load.xmlSave;
import org.springframework.web.bind.annotation.*;
import com.omarkhaled.paint.undo_redo.UndoRedo;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

@RestController
@RequestMapping
@CrossOrigin
public class ShapeController {
    ShapeService shapeService;

    public ShapeController(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    //create new shape (factory)
    @PostMapping ("/create")
    public void createShape(@RequestBody String object) throws JsonProcessingException {
        ShapeFactory shapeFactory = new ShapeFactory();
        ObjectMapper mapper = new ObjectMapper();
        String[] type = object.split("\\{");
        Shape shape = shapeFactory.createShape(type[0]);
        type[1] = "{" + type[1];
        switch (type[0]){
            case "Circle" -> {
                Circle circle = (Circle)shape;
                circle = mapper.readValue(type[1], Circle.class);
                shapeService.addToUndo(circle);
                shapeService.addToMap(circle);
            }
            case "Square"-> {
                Square Square = (Square) shape;
                Square = mapper.readValue(type[1], Square.class);
                shapeService.addToUndo(Square);
                shapeService.addToMap(Square);
            }
            case "Rectangle" -> {
                Rectangle Rectangle = (Rectangle) shape;
                Rectangle = mapper.readValue(type[1], Rectangle.class);
                shapeService.addToUndo(Rectangle);
                shapeService.addToMap(Rectangle);
            }
            case "Line" -> {
                Line line = (Line) shape;
                line = mapper.readValue(type[1], Line.class);
                shapeService.addToUndo(line);
                shapeService.addToMap(line);
            }
            case "Ellipse" -> {
                Elipse elipse = (Elipse) shape;
                elipse = mapper.readValue(type[1], Elipse.class);
                shapeService.addToUndo(elipse);
                shapeService.addToMap(elipse);
            }
            case "Triangle" ->{
                Triangle triangle = (Triangle) shape;
                triangle = mapper.readValue(type[1], Triangle.class);
                shapeService.addToUndo(triangle);
                shapeService.addToMap(triangle);
            }
            case "Star" -> {
                Star star = (Star) shape;
                star = mapper.readValue(type[1], Star.class);
                shapeService.addToUndo(star);
                shapeService.addToMap(star);
            }
            case "RegularPolygon" -> {
                RegularPolygon regularPolygon = (RegularPolygon) shape;
                regularPolygon = mapper.readValue(type[1], RegularPolygon.class);
                shapeService.addToUndo(regularPolygon);
                shapeService.addToMap(regularPolygon);
            }
            default -> {}
        }
    }

    //copy shape (prototype)
    @PostMapping ("/copy")
    public Shape copyShape(@RequestBody String id){
        Shape shape = shapeService.getShapes().get(id).peek().Clone();
        shape.setIndex(Integer.toString(shapeService.getShapes().size()));
        shape.setX(shape.getX() + 3.0);
        shape.setY(shape.getY() + 3.0);
        shapeService.addToUndo(shape);
        shapeService.addToMap(shape);
        return shape;
    }

    //delete shape
    @PostMapping ("/delete")
    public void delete(@RequestBody String object){
        ShapeFactory shapeFactory = new ShapeFactory();
        String[] strings = object.split(",");
        String id = strings[0];
        Shape shape = shapeFactory.createShape(strings[1]); // strings[1] -> type
        switch (strings[1]){
            case "Circle" -> {
                Circle circle = (Circle)shape;
                circle.setIndex(id);
                shapeService.addToUndo(circle);
                shapeService.getShapes().get(id).push(circle);
            }
            case "Square" -> {
                Square Square = (Square) shape;
                Square.setIndex(id);
                shapeService.addToUndo(Square);
                shapeService.getShapes().get(id).push(Square);
            }
            case "Rectangle" -> {
                Rectangle Rectangle = (com.omarkhaled.paint.shape.Rectangle) shape;
                Rectangle.setIndex(id);
                shapeService.addToUndo(Rectangle);
                shapeService.getShapes().get(id).push(Rectangle);
            }
            case "Line" -> {
                Line line = (Line) shape;
                line.setIndex(id);
                shapeService.addToUndo(line);
                shapeService.getShapes().get(id).push(line);
            }
            case "Ellipse" -> {
                Elipse elipse = (Elipse) shape;
                elipse.setIndex(id);
                shapeService.addToUndo(elipse);
                shapeService.getShapes().get(id).push(elipse);
            }
            case "Triangle" ->{
                Triangle triangle = (Triangle) shape;
                triangle.setIndex(id);
                shapeService.addToUndo(triangle);
                shapeService.getShapes().get(id).push(triangle);
            }
            case "Star" -> {
                Star star = (Star) shape;
                star.setIndex(id);
                shapeService.addToUndo(star);
                shapeService.getShapes().get(id).push(star);
            }
            case "RegularPolygon" -> {
                RegularPolygon regularPolygon = (RegularPolygon) shape;
                regularPolygon.setIndex(id);
                shapeService.addToUndo(regularPolygon);
                shapeService.getShapes().get(id).push(regularPolygon);
            }
            default -> {}
        }
        shapeService.setListFromMap();
    }

    //modify shape
    @PostMapping ("/modify")
    public void modifyShape(@RequestBody String object) throws JsonProcessingException {
        ShapeFactory shapeFactory = new ShapeFactory();
        ObjectMapper mapper = new ObjectMapper();
        String[] type = object.split("\\{");
        String id = type[0];
        type[2] = "{" + type[2];
        Shape shape = shapeFactory.createShape(type[1]); // strings[1] -> type
        switch (type[1]){
            case "Circle" -> {
                Circle circle = (Circle)shape;
                circle = mapper.readValue(type[2], Circle.class);
                circle.setIndex(id);
                shapeService.addToUndo(circle);
                shapeService.getShapes().get(id).push(circle);
            }
            case "Square" -> {
                Square Square = (Square) shape;
                Square = mapper.readValue(type[2], Square.class);
                Square.setIndex(id);
                shapeService.addToUndo(Square);
                shapeService.getShapes().get(id).push(Square);
            }
            case "Rectangle" -> {
                Rectangle Rectangle = (com.omarkhaled.paint.shape.Rectangle) shape;
                Rectangle = mapper.readValue(type[2], com.omarkhaled.paint.shape.Rectangle.class);
                Rectangle.setIndex(id);
                shapeService.addToUndo(Rectangle);
                shapeService.getShapes().get(id).push(Rectangle);
            }
            case "Line" -> {
                Line line = (Line) shape;
                line = mapper.readValue(type[2], Line.class);
                line.setIndex(id);
                shapeService.addToUndo(line);
                shapeService.getShapes().get(id).push(line);
            }
            case "Ellipse" -> {
                Elipse elipse = (Elipse) shape;
                elipse = mapper.readValue(type[2], Elipse.class);
                elipse.setIndex(id);
                shapeService.addToUndo(elipse);
                shapeService.getShapes().get(id).push(elipse);
            }
            case "Triangle" ->{
                Triangle triangle = (Triangle) shape;
                triangle = mapper.readValue(type[2], Triangle.class);
                triangle.setIndex(id);
                shapeService.addToUndo(triangle);
                shapeService.getShapes().get(id).push(triangle);
            }
            case "Star" -> {
                Star star = (Star) shape;
                star = mapper.readValue(type[2], Star.class);
                star.setIndex(id);
                shapeService.addToUndo(star);
                shapeService.getShapes().get(id).push(star);
            }
            case "RegularPolygon" -> {
                RegularPolygon regularPolygon = (RegularPolygon) shape;
                regularPolygon = mapper.readValue(type[2], RegularPolygon.class);
                regularPolygon.setIndex(id);
                shapeService.addToUndo(regularPolygon);
                shapeService.getShapes().get(id).push(regularPolygon);
            }
            default -> {}
        }
    }

    //clear all shapes
    @GetMapping ("/clear")
    public void clear (){
        shapeService.setGeneralIndex(0L);
        shapeService.setRedo(new Stack<>());
        shapeService.setUndo(new Stack<>());
        shapeService.setShapes(new HashMap<>());
    }

    //save json file
    @PostMapping ("/saveJson")
    public void saveJson(@RequestBody String path){
        List<Shape> list = new ArrayList<>();
        shapeService.setList(list);
        shapeService.saveShapes();
        jsonSave jsonSave = new jsonSave(shapeService);
        jsonSave.save(Paths.get(path));
    }

    //save xml file
    @PostMapping ("/saveXml")
    public void saveXml(@RequestBody String path){
        List<Shape> list = new ArrayList<>();
        shapeService.setList(list);
        shapeService.saveShapes();
        xmlSave xmlSave = new xmlSave(shapeService);
        xmlSave.save(Paths.get(path));
    }

    //load json file
    @PostMapping ("/loadJson")
    public List<Shape> loadJson(@RequestBody String path){
        jsonLoad jsonLoad = new jsonLoad(shapeService);
        jsonLoad.load(Paths.get(path));
        return shapeService.getList();
    }

    //load json file
    @PostMapping ("/loadXml")
    //load xml file
    public List<Shape> loadXml(@RequestBody String path){
        xmlLoad xmlLoad = new xmlLoad(shapeService);
        xmlLoad.load(Paths.get(path));
        return shapeService.getList();
    }

    //undo
    @GetMapping ("/undo")
    public List<Shape> shapesUndo(){
        UndoRedo undoRedo = new UndoRedo();
        List<Shape> list = new ArrayList<>();
        undoRedo.Undo(shapeService.getUndo(), shapeService.getRedo(), shapeService.getShapes());
        shapeService.setList(list);
        shapeService.setListFromMap();
        return shapeService.getList();
    }

    //redo
    @GetMapping ("/redo")
    public List<Shape> shapesRedo(){
        UndoRedo undoRedo = new UndoRedo();
        List<Shape> list = new ArrayList<>();
        undoRedo.Redo(shapeService.getUndo(), shapeService.getRedo(), shapeService.getShapes());
        shapeService.setList(list);
        shapeService.setListFromMap();
        return shapeService.getList();
    }

}
