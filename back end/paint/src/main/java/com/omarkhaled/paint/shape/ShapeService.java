package com.omarkhaled.paint.shape;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@XmlRootElement(name = "ArrayList")
public class ShapeService {
    private List<Shape> list = new ArrayList<>();

    private Stack<Shape> Undo = new Stack<>();

    private Stack<Shape>Redo = new Stack<>();
    private Map<String, Stack<Shape>> Shapes = new HashMap<>();

    private Long generalIndex = 0L;

    public Long getGeneralIndex() {
        return generalIndex;
    }

    public void setGeneralIndex(Long generalIndex) {
        this.generalIndex = generalIndex;
    }

    public void incrementGeneralIndex() {
        this.generalIndex++;
    }

    public void setUndo(Stack<Shape> undo) {
        Undo = undo;
    }

    public void setRedo(Stack<Shape> redo) {
        Redo = redo;
    }

    public void setShapes(Map<String, Stack<Shape>> shapes) {
        Shapes = shapes;
    }

    //set list
    public void setList(List<Shape> list) {
        this.list = list;
    }

    @XmlElement(name = "item")
    public List<Shape> getList() {
        return list;
    }

    public Stack<Shape> getUndo() {
        return Undo;
    }

    public Stack<Shape> getRedo() {
        return Redo;
    }
    public Map<String, Stack<Shape>> getShapes() {
        return Shapes;
    }

    public void setListFromMap(){
        for(Map.Entry<String, Stack<Shape>> entry : Shapes.entrySet()){
            if(!entry.getValue().empty())
                list.add(entry.getValue().peek());
            else
                list.add(null);
        }
    }

    public void saveShapes(){
        for(Map.Entry<String, Stack<Shape>> entry : Shapes.entrySet())
            if (!entry.getValue().empty())
                list.add(entry.getValue().peek());
    }

    public void setMapFromList(){
        for (Shape shape : list) {
            Stack<Shape> stack = new Stack<>();
            stack.push(shape);
            Shapes.put(generalIndex.toString(), stack);
            generalIndex++;
        }
    }

    public void modifyIndex(){
        for (Shape shape : list){
            shape.setId(generalIndex.toString());
            generalIndex++;
        }
        generalIndex = 0L;
    }


    //add new stake for the created shape
    public void addToMap(Shape shape){
        Stack<Shape> newStack = new Stack<>();
        newStack.push(shape);
        Shapes.put(shape.getId(), newStack);
    }

    //add to undo stack
    public void addToUndo(Shape shape){
        Undo.push(shape);
    }
}
