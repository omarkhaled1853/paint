import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

class UndoRedo {
    Stack<Shape>Undo;
    Stack<Shape>Redo;
    Map<Long, Stack<Shape>> Shapes;
    List<Shape> finalShapes;
    void Undo(){
        if(Undo.empty())
            return;

        Shape LastShape = Undo.peek();
        Undo.pop();
        long index = LastShape.getIndex();


        Redo.push(LastShape);
        Shapes.get(index).pop();

        finalShapes = new ArrayList<>();

        for(Map.Entry<Long, Stack<Shape>> entry : Shapes.entrySet()){
            if(!entry.getValue().empty())
                finalShapes.add(entry.getValue().peek());
        }
    }
    void Redo(){
        if(Redo.empty())
            return;

        Shape LastShape = Redo.peek();
        Redo.pop();
        long index = LastShape.getIndex();


        Undo.push(LastShape);
        Shapes.get(index).push(LastShape);

        finalShapes = new ArrayList<>();
        for(Map.Entry<Long, Stack<Shape>> entry : Shapes.entrySet()){
            finalShapes.add(entry.getValue().peek());
        }
    }
}

