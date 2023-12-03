package com.omarkhaled.paint.shape;

import com.omarkhaled.paint.save_load.jsonLoad;
import com.omarkhaled.paint.save_load.jsonSave;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
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

    //save json test
//        @GetMapping
//    public String saved(){
//        jsonSave jsonSave = new jsonSave(shapeService);
//        jsonSave.save();
//        return "saved";
//    }

    //load json test
//    @GetMapping
//    public List<Shape> shapes(){
//        jsonLoad jsonLoad = new jsonLoad(shapeService);
//        jsonLoad.load();
//        return shapeService.getList();
//    }

//    @GetMapping
//    public List<Shape> test(){
//        List<Shape> list = new ArrayList<>();
//        Shape shape = new Circle();
//        Circle circle = (Circle) shape;
//        circle.setName("circle");
//        circle.setX(0);
//        circle.setY(0);
//        circle.setFill("red");
//        circle.setStroke("blue");
//        circle.setRadius(10);
//        list.add(circle);
//        return list;
//    }

}
