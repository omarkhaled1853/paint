package com.omarkhaled.paint.shape;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ShapeService {
    private List<Shape> list = new ArrayList<>();

    private Stack<Shape> Undo = new Stack<>();

    private Stack<Shape>Redo = new Stack<>();
    private Map<Long, Stack<Shape>> Shapes = new HashMap<>();

    private Long generalIndex = 0L;

    public Long getGeneralIndex() {
        return generalIndex;
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

    public void setShapes(Map<Long, Stack<Shape>> shapes) {
        Shapes = shapes;
    }

    //set list
    public void setList(List<Shape> list) {
        this.list = list;
    }

    public List<Shape> getList() {
        return list;
    }

    public Stack<Shape> getUndo() {
        return Undo;
    }

    public Stack<Shape> getRedo() {
        return Redo;
    }
    public Map<Long, Stack<Shape>> getShapes() {
        return Shapes;
    }

    public void setListFromMap(){
        for(Map.Entry<Long, Stack<Shape>> entry : Shapes.entrySet()){
            if(!entry.getValue().empty())
                list.add(entry.getValue().peek());
            else
                list.add(null);
        }
    }


    //add new stake for the created shape
    public void addToMap(Shape shape){
        Stack<Shape> newStack = new Stack<>();
        newStack.push(shape);
//        System.out.println(generalIndex);
        Shapes.put(generalIndex, newStack);
    }

    //add to undo stack
    public void addToUndo(Shape shape){
        Undo.push(shape);
    }
}
