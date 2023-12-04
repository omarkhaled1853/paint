package com.omarkhaled.paint.shape;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.omarkhaled.paint.save_load.jsonLoad;
import com.omarkhaled.paint.save_load.jsonSave;
import com.omarkhaled.paint.save_load.xmlSave;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.web.bind.annotation.*;
import undo_redo.UndoRedo;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping
@CrossOrigin
public class ShapeController {
    ShapeService shapeService;

    public ShapeController(ShapeService shapeService) {
        this.shapeService = shapeService;
        //for test save
//        List<Shape> list = new ArrayList<>();
//        Circle circle = new Circle();
//        Quadrilateral quadrilateral = new Quadrilateral();
//
//        circle.setIndex(1);
//        circle.setX(0);
//        circle.setY(0);
//        circle.setFill("red");
//        circle.setStroke("blue");
//        circle.setStrokeWidth(5);
//        circle.setRadius(6562);
//
//        quadrilateral.setIndex(2);
//        quadrilateral.setX(10);
//        quadrilateral.setY(20);
//        quadrilateral.setFill("green");
//        quadrilateral.setStroke("pink");
//        quadrilateral.setStrokeWidth(15);
//        quadrilateral.setWidth(54);
//        quadrilateral.setHeight(48);
//
//        list.add(circle);
//        list.add(quadrilateral);
//        shapeService.setList(list);
    }

//    save json
//    @PostMapping ("/save")
//    public void saved(@RequestBody String path){
////        System.out.println(path);
//        jsonSave jsonSave = new jsonSave(shapeService);
//        jsonSave.save(Paths.get(path));
//    }

    @PostMapping ("/save")
    public String save(@RequestBody String path){
        xmlSave xmlSave = new xmlSave(shapeService);
        xmlSave.save(Paths.get(path));
        return "saved";
    }

    @PostMapping ("/create")
    public void createShape(@RequestBody String object) throws JsonProcessingException {
        ShapeFactory shapeFactory = new ShapeFactory();
        ObjectMapper mapper = new ObjectMapper();
        String[] type = object.split("\\{");
//        System.out.println(type[0]);
        Shape shape = shapeFactory.createShape(type[0]);
        shape.incrementIndex();
        type[1] = "{" + type[1];
        switch (type[0]){
            case "Circle" -> {
                Circle circle = (Circle)shape;
                circle = mapper.readValue(type[1], Circle.class);
                shapeService.addToUndo(circle);
                shapeService.addToMap(circle);
            }
            case "Square", "Rectangle" -> {
                Quadrilateral quadrilateral = (Quadrilateral) shape;
                quadrilateral = mapper.readValue(type[1], Quadrilateral.class);
                shapeService.addToUndo(quadrilateral);
                shapeService.addToMap(quadrilateral);
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
            default -> {}
        }
//        shapeService.addToList(shape);
//        System.out.println(shapeService.getList());
    }

    @PostMapping ("/undo")
    public List<Shape> shapesUndo(){
        UndoRedo undoRedo = new UndoRedo();
        undoRedo.Undo(shapeService.getUndo(), shapeService.getRedo(), shapeService.getShapes());
        shapeService.setList();
        return shapeService.getList();
    }

//    load json
//    @GetMapping ("/{load}")
//    public List<Shape> shapes(){
//        jsonLoad jsonLoad = new jsonLoad(shapeService);
//        jsonLoad.load();
//        return shapeService.getList();
//    }
}
