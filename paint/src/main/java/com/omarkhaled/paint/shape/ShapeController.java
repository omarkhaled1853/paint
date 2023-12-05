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
                circle.setIndex(shapeService.getGeneralIndex());
                shapeService.addToUndo(circle);
                shapeService.addToMap(circle);
            }
            case "Square", "Rectangle" -> {
                Quadrilateral quadrilateral = (Quadrilateral) shape;
                quadrilateral = mapper.readValue(type[1], Quadrilateral.class);
                quadrilateral.setIndex(shapeService.getGeneralIndex());
                shapeService.addToUndo(quadrilateral);
                shapeService.addToMap(quadrilateral);
            }
            case "Line" -> {
                Line line = (Line) shape;
                line = mapper.readValue(type[1], Line.class);
                line.setIndex(shapeService.getGeneralIndex());
                shapeService.addToUndo(line);
                shapeService.addToMap(line);
            }
            case "Ellipse" -> {
                Elipse elipse = (Elipse) shape;
                elipse = mapper.readValue(type[1], Elipse.class);
                elipse.setIndex(shapeService.getGeneralIndex());
                shapeService.addToUndo(elipse);
                shapeService.addToMap(elipse);
            }
            case "Triangle" ->{
                Triangle triangle = (Triangle) shape;
                triangle = mapper.readValue(type[1], Triangle.class);
                triangle.setIndex(shapeService.getGeneralIndex());
                shapeService.addToUndo(triangle);
                shapeService.addToMap(triangle);
            }
            default -> {}
        }
        shapeService.incrementGeneralIndex();
    }

    //copy shape (prototype)
    @PostMapping ("/copy")
    public Shape copyShape(@RequestBody String index){
        System.out.println(index);
        Shape shape = shapeService.getShapes().get(Long.parseLong(index)).peek().Clone();
        shape.setIndex(shapeService.getGeneralIndex());
        shape.setX(shape.getX() + 1.0);
        shape.setY(shape.getY() + 1.0);
        shapeService.addToUndo(shape);
        shapeService.addToMap(shape);
        shapeService.incrementGeneralIndex();
        return shape;
    }

    //delete shape
    @PostMapping ("/delete")
    public void delete(@RequestBody String object){
        ShapeFactory shapeFactory = new ShapeFactory();
        String[] strings = object.split(",");
        long index = Long.parseLong(strings[0]);
        String type = strings[1];
        Shape shape = shapeFactory.createShape(strings[1]);
        switch (strings[1]){
            case "Circle" -> {
                Circle circle = (Circle)shape;
                circle.setIndex(index);
                shapeService.addToUndo(circle);
                shapeService.getShapes().get(index).push(circle);
            }
            case "Square", "Rectangle" -> {
                Quadrilateral quadrilateral = (Quadrilateral) shape;
                quadrilateral.setIndex(index);
                shapeService.addToUndo(quadrilateral);
                shapeService.getShapes().get(index).push(quadrilateral);
            }
            case "Line" -> {
                Line line = (Line) shape;
                line.setIndex(index);
                shapeService.addToUndo(line);
                shapeService.getShapes().get(index).push(line);
            }
            case "Ellipse" -> {
                Elipse elipse = (Elipse) shape;
                elipse.setIndex(index);
                shapeService.addToUndo(elipse);
                shapeService.getShapes().get(index).push(elipse);
            }
            case "Triangle" ->{
                Triangle triangle = (Triangle) shape;
                triangle.setIndex(index);
                shapeService.addToUndo(triangle);
                shapeService.getShapes().get(index).push(triangle);
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
        Long index = Long.parseLong(type[0]);
        type[2] = "{" + type[2];
        Shape shape = shapeFactory.createShape(type[1]);
        switch (type[1]){
            case "Circle" -> {
                Circle circle = (Circle)shape;
                circle = mapper.readValue(type[2], Circle.class);
                circle.setIndex(index);
                shapeService.addToUndo(circle);
                shapeService.getShapes().get(index).push(circle);
            }
            case "Square", "Rectangle" -> {
                Quadrilateral quadrilateral = (Quadrilateral) shape;
                quadrilateral = mapper.readValue(type[2], Quadrilateral.class);
                quadrilateral.setIndex(index);
                shapeService.addToUndo(quadrilateral);
                shapeService.getShapes().get(index).push(quadrilateral);
            }
            case "Line" -> {
                Line line = (Line) shape;
                line = mapper.readValue(type[2], Line.class);
                line.setIndex(index);
                shapeService.addToUndo(line);
                shapeService.getShapes().get(index).push(line);
            }
            case "Ellipse" -> {
                Elipse elipse = (Elipse) shape;
                elipse = mapper.readValue(type[2], Elipse.class);
                elipse.setIndex(index);
                shapeService.addToUndo(elipse);
                shapeService.getShapes().get(index).push(elipse);
            }
            case "Triangle" ->{
                Triangle triangle = (Triangle) shape;
                triangle = mapper.readValue(type[2], Triangle.class);
                triangle.setIndex(index);
                shapeService.addToUndo(triangle);
                shapeService.getShapes().get(index).push(triangle);
            }
            default -> {}
        }
    }

    //save json file
    @PostMapping ("/saveJson")
    public void saveJson(@RequestBody String path){
        jsonSave jsonSave = new jsonSave(shapeService);
        jsonSave.save(Paths.get(path));
    }

    //save xml file
    @PostMapping ("/saveXml")
    public String saveXml(@RequestBody String path){
        xmlSave xmlSave = new xmlSave(shapeService);
        xmlSave.save(Paths.get(path));
        return "saved";
    }

    //load json file
    @GetMapping ("/loadJson")
    public List<Shape> loadJson(@RequestBody String path){
        jsonLoad jsonLoad = new jsonLoad(shapeService);
        jsonLoad.load(Paths.get(path));
        return shapeService.getList();
    }

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

//        System.out.println(shapeService.getUndo());
//        System.out.println(shapeService.getRedo());
//        System.out.println(shapeService.getShapes());

        undoRedo.Undo(shapeService.getUndo(), shapeService.getRedo(), shapeService.getShapes());
        shapeService.setList(list);
        shapeService.setListFromMap();

//        System.out.println(shapeService.getUndo());
//        System.out.println(shapeService.getRedo());
//        System.out.println(shapeService.getShapes());

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
