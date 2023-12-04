package com.omarkhaled.paint.shape;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

@Service
public class ShapeService {
    private List<Shape> list = new ArrayList<>();

    private Stack<Shape> Undo;

    private Stack<Shape>Redo;
    private Map<Long, Stack<Shape>> Shapes;


    public List<Shape> getList() {
        return list;
    }

    public void setList(List<Shape> list) {

        this.list = list;
    }
    public void setList(){
        for(Map.Entry<Long, Stack<Shape>> entry : Shapes.entrySet()){
            if(!entry.getValue().empty())
                list.add(entry.getValue().peek());
            else
                list.add(null);
        }
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

    public  void addToMap(Shape shape){
        Stack<Shape> newStack = new Stack<>();
        newStack.push(shape);
        Shapes.put(shape.getIndex(), newStack);
    }

    public void addToUndo(Shape shape){
        Undo.push(shape);
    }

    public void undo(){
        for(Map.Entry<Long, Stack<Shape>> entry : Shapes.entrySet()){
            if(!entry.getValue().empty())
                list.add(entry.getValue().peek());
            else{
                list.add(null);
            }
        }
    }

    public void redo(){
        for(Map.Entry<Long, Stack<Shape>> entry : Shapes.entrySet()){
            list.add(entry.getValue().peek());
        }
    }
}
