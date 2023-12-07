package com.omarkhaled.paint.save_load;
import com.omarkhaled.paint.shape.Shape;
import com.omarkhaled.paint.shape.ShapeService;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class xmlLoad {
    ShapeService shapeService;

    public xmlLoad(ShapeService shapeService) {
        this.shapeService = shapeService;
    }

    //custom load
    public void load(Path path){
        FileInputStream fis;
        try {
            fis = new FileInputStream(path.toFile());
            XMLDecoder decoder = new XMLDecoder(fis);
            List<Shape> list = new ArrayList<>();
            while (true){
                try {
                    Object object = decoder.readObject();
                    list.add((Shape) object);
                } catch (ArrayIndexOutOfBoundsException e) {
                    break;
                }
            }
            shapeService.setList(list);
            shapeService.setGeneralIndex(0L);
            shapeService.setUndo(new Stack<>());
            shapeService.setRedo(new Stack<>());
            shapeService.setShapes(new HashMap<>());
            shapeService.modifyIndex();
            shapeService.setMapFromList();
        } catch (FileNotFoundException e) {
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
