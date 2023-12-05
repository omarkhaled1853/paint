package com.omarkhaled.paint.undo_redo;

import com.omarkhaled.paint.shape.Shape;

import java.util.Map;
import java.util.Stack;

public class UndoRedo {
    public void Undo(Stack<Shape> undo, Stack<Shape> redo, Map<Long, Stack<Shape>> shapes){
        if(undo.empty())
            return;

        //pop from undo
        Shape LastShape = undo.peek();
        undo.pop();
        long index = LastShape.getIndex();
//        System.out.println(index);
        //push in redo and pop from map
        redo.push(LastShape);
        shapes.get(index).pop();
    }
    public void Redo(Stack<Shape> undo, Stack<Shape> redo, Map<Long, Stack<Shape>> shapes){
        if(redo.empty())
            return;

        //pop from redo
        Shape LastShape = redo.peek();
        redo.pop();
        long index = LastShape.getIndex();

        //push in undo and map
        undo.push(LastShape);
        shapes.get(index).push(LastShape);
    }
}
