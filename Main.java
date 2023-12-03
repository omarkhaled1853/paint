import java.util.IdentityHashMap;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShapeFactory shapeFactory = new ShapeFactory();

        UndoRedo undoRedo = new UndoRedo();
        undoRedo.Undo = new Stack<>();
        undoRedo.Redo = new Stack<>();
        undoRedo.Shapes = new IdentityHashMap<>();
        while (true){
            String shapeType = scanner.nextLine();
            if(shapeType.equals("br"))
                break;
            Shape shape = shapeFactory.createShape(shapeType);
            switch (shape.getType()) {
                case "Line" -> {
                    Line line = (Line) shape;
                    line.setX(5);
                    line.setY(6);
                    line.setX2(4);
                    line.setY2(3);
                    line.setStroke("black");
                    line.setStrokeWidth(32.2);
                    line.setFill("blue");
                    line.setIndex(1);
                    undoRedo.Undo.push(line);
                    if(undoRedo.Shapes.containsKey(line.getIndex())){
                        undoRedo.Shapes.get(line.getIndex()).push(line);
                    }
                    else {
                        Stack<Shape> lineShapes = new Stack<>();
                        lineShapes.push(line);
                        undoRedo.Shapes.put(line.getIndex(), lineShapes);
                    }
                }
                case "Circle" -> {
                    Circle circle = (Circle) shape;
                    circle.setIndex(2);
                    circle.setX(5);
                    circle.setY(4);
                    circle.setFill("red");
                    circle.setStroke("black");
                    circle.setStrokeWidth(16);
                    circle.setRadius(32.22);
                    undoRedo.Undo.push(circle);
                    if(undoRedo.Shapes.containsKey(circle.getIndex())){
                        undoRedo.Shapes.get(circle.getIndex()).push(circle);
                    }
                    else {
                        Stack<Shape> circleShapes = new Stack<>();
                        circleShapes.push(circle);
                        undoRedo.Shapes.put(circle.getIndex(), circleShapes);
                    }
                }
                case "Ellipse" -> {
                    Ellipse ellipse = (Ellipse) shape;

                    ellipse.setX(5);
                    ellipse.setY(6);
                    ellipse.setStroke("black");
                    ellipse.setStrokeWidth(32.2);
                    ellipse.setFill("blue");
                    ellipse.setIndex(3);
                    ellipse.setRadiusX(32);
                    ellipse.setRadiusY(22);
                    undoRedo.Undo.push(ellipse);
                    if(undoRedo.Shapes.containsKey(ellipse.getIndex())){
                        undoRedo.Shapes.get(ellipse.getIndex()).push(ellipse);
                    }
                    else {
                        Stack<Shape> ellipseShapes = new Stack<>();
                        ellipseShapes.push(ellipse);
                        undoRedo.Shapes.put(ellipse.getIndex(), ellipseShapes);
                    }
                }
                case "Triangle" -> {
                    Triangle triangle = (Triangle) shape;
                    triangle.setX(5);
                    triangle.setY(6);
                    triangle.setStroke("black");
                    triangle.setStrokeWidth(32.2);
                    triangle.setFill("blue");
                    triangle.setIndex(4);
                    triangle.setRadius(20);
                    undoRedo.Undo.push(triangle);
                    if(undoRedo.Shapes.containsKey(triangle.getIndex())){
                        undoRedo.Shapes.get(triangle.getIndex()).push(triangle);
                    }
                    else {
                        Stack<Shape> triangleShapes = new Stack<>();
                        triangleShapes.push(triangle);
                        undoRedo.Shapes.put(triangle.getIndex(), triangleShapes);
                    }
                }
                case "Rectangle" -> {
                    Rectangle rectangle = (Rectangle) shape;
                    rectangle.setIndex(5);
                    rectangle.setX(43.3);
                    rectangle.setY(44.3);
                    rectangle.setFill("blue");
                    rectangle.setStroke("block");
                    rectangle.setStrokeWidth(34.32);
                    rectangle.setWidth(5);
                    rectangle.setHeight(33.2);
                    undoRedo.Undo.push(rectangle);
                    if(undoRedo.Shapes.containsKey(rectangle.getIndex())){
                        undoRedo.Shapes.get(rectangle.getIndex()).push(rectangle);
                    }
                    else {
                        Stack<Shape> rectangleShapes = new Stack<>();
                        rectangleShapes.push(rectangle);
                        undoRedo.Shapes.put(rectangle.getIndex(), rectangleShapes);
                    }
                }
                case "Square" -> {
                    Square square = (Square) shape;
                    square.setX(3);
                    square.setY(4);
                    square.setStroke("black");
                    square.setStrokeWidth(3.343);
                    square.setFill("yellow");
                    square.setRadius(332.2);
                    square.setIndex(6);
                    undoRedo.Undo.push(square);
                    if(undoRedo.Shapes.containsKey(square.getIndex())){
                        undoRedo.Shapes.get(square.getIndex()).push(square);
                    }
                    else {
                        Stack<Shape> squareShapes = new Stack<>();
                        squareShapes.push(square);
                        undoRedo.Shapes.put(square.getIndex(), squareShapes);
                    }
                }
                default -> throw new RuntimeException();
            }
        }
//        System.out.println(undoRedo.Undo.peek().getType());
       // System.out.println(undoRedo.Shapes.containsKey(1L));
//        System.out.println(undoRedo.Shapes.get(1L).peek().getFill());
        undoRedo.Undo();
//        for(Shape shape : undoRedo.Undo){
//            System.out.println(shape.getType());
//        }
        for(Shape shape : undoRedo.finalShapes){
            System.out.println(shape.getType());
        }
        System.out.println("hhhh");
        undoRedo.Redo();
        for(Shape shape : undoRedo.finalShapes){
            System.out.println(shape.getType());
        }
//        for(Shape shape : undoRedo.Redo){
//            System.out.println(shape.getType());
//        }
    }
}
//        Shape shape = shapeFactory.createShape("Rectangle");
//        Rectangle rectangle = (Rectangle) shape;
//        rectangle.setIndex(25);
//        rectangle.setX(43.3);
//        rectangle.setY(44.3);
//        rectangle.setFill("blue");
//        rectangle.setStroke("block");
//        rectangle.setStrokeWidth(34.32);
//        rectangle.setWidth(5);
//        rectangle.setHeight(33.2);
//        undoRedo.Undo.push(rectangle);
//        if(undoRedo.Shapes.containsKey(rectangle.getIndex())){
//            undoRedo.Shapes.get(rectangle.getIndex()).push(rectangle);
//        }
//        else {
//            Stack<Shape> rectangleShapes = new Stack<>();
//            rectangleShapes.push(rectangle);
//            undoRedo.Shapes.put(rectangle.getIndex(), rectangleShapes);
//        }
//       // long i = 1;
//        Shape shape = shapeFactory.createShape("Line");
//        Line line = (Line) shape;
//        line.setX(5);
//        line.setY(6);
//        line.setX2(4);
//        line.setY2(3);
//        line.setStroke("black");
//        line.setStrokeWidth(32.2);
//        line.setFill("red");
//        line.setIndex(1);
//        undoRedo.Undo.push(line);
//        if(undoRedo.Shapes.containsKey(line.getIndex())){
//            undoRedo.Shapes.get(line.getIndex()).push(line);
//        }
//        else {
//            Stack<Shape> lineShapes = new Stack<>();
//            lineShapes.push(line);
//            undoRedo.Shapes.put(line.getIndex(), lineShapes);
//        }
