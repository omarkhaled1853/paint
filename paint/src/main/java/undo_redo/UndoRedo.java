package undo_redo;

import com.omarkhaled.paint.shape.Shape;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class UndoRedo {

//    List<Shape> finalShapes;
    public void Undo(Stack<Shape> undo, Stack<Shape> redo, Map<Long, Stack<Shape>> shapes){
        if(undo.empty())
            return;

        Shape LastShape = undo.peek();
        undo.pop();
        long index = LastShape.getIndex();


        redo.push(LastShape);
        shapes.get(index).pop();

//        finalShapes = new ArrayList<>();
//
//        for(Map.Entry<Long, Stack<Shape>> entry : Shapes.entrySet()){
//            if(!entry.getValue().empty())
//                finalShapes.add(entry.getValue().peek());
//            else{
//                finalShapes.add(null);
//            }
//        }
    }
    public void Redo(Stack<Shape> undo, Stack<Shape> redo, Map<Long, Stack<Shape>> shapes){
        if(redo.empty())
            return;

        Shape LastShape = redo.peek();
        redo.pop();
        long index = LastShape.getIndex();


        undo.push(LastShape);
        shapes.get(index).push(LastShape);

//        finalShapes = new ArrayList<>();
//        for(Map.Entry<Long, Stack<Shape>> entry : Shapes.entrySet()){
//            finalShapes.add(entry.getValue().peek());
//        }
    }
}
