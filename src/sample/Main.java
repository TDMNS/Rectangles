package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Drawing a Rectangle
        Rectangle rectangle = new Rectangle(50, 50, 100, 200);
        Rectangle rectangle2 = new Rectangle(50, 50, 100, 200);
        Rectangle rectangle3 = new Rectangle();
        rectangle.move(300, 300);
        rectangle3= rectangle2.union(rectangle, rectangle2);
//        DrawableRect drawableRect = new DrawableRect(rectangle3, Color.BLUEVIOLET);
        ColoredRect coloredRect1 = new ColoredRect(rectangle, Color.DARKSEAGREEN, Color.BLUE);
        ColoredRect coloredRect2 = new ColoredRect(rectangle2, Color.BLUE, Color.BLANCHEDALMOND);
        ColoredRect coloredRect3 = new ColoredRect(rectangle3, Color.DEEPPINK, Color.TRANSPARENT);
        //Creating a Group object

        Group root = new Group(coloredRect1.draw(), coloredRect2.draw(), coloredRect3.draw());

        //Creating a scene object
        Scene scene = new Scene(root, 1920, 1000);
        //Setting title to the Stage
        primaryStage.setTitle("Drawing a Rectangle");
        //Adding scene to the stage
        primaryStage.setScene(scene);

        //Displaying the contents of the stage
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

class Rectangle{

    //Левый верхний угол
    protected int x1;
    protected int y1;

    //Правый нижний угол
    protected int x2;
    protected int y2;

    //Ширина и высота
    protected int height;
    protected int width;

    Rectangle(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        height = y2 - y1;
        width = x2 - x1;
    }

    Rectangle(int height, int width){
        //помещаем в левый верхний угол
        this.x1 = 0;
        this.y1 = 0;

        this.height = height;
        this.width = width;

        this.x2 = x1 + width;
        this.y2 = y1 + height;
    }

    Rectangle(){
        // Создаём вырожденный прямоугольник
        x1 = 0;
        y1 = 0;
        x2 = 0;
        y2 = 0;
    }

    // создаём метод для отображения состояния экземпляра класса

    void rect_print(){
        System.out.print("x1 = ");
        System.out.print(x1);
        System.out.print(", ");
        System.out.print("y1 = ");
        System.out.print(y1);
        System.out.println(";");
        System.out.print("x2 = ");
        System.out.print(x2);
        System.out.print(", ");
        System.out.print("y2 = ");
        System.out.print(y2);
        System.out.println(";");
    }

    void move(int dx, int dy){
        // левый верхний угол
        x1 += dx;
        y1 += dy;
        // правый нижний угол
        x2 += dx;
        y2 += dy;
    }

    public Rectangle union(Rectangle a, Rectangle b) {

//        return new Rectangle(
//                30,
//                30,
//                120,
//                120);

        return new Rectangle(
                this.x1 = Math.min(a.x1, b.x1),
                this.y1 = Math.min(a.y1, b.y1),
                this.x2 = Math.max(a.x2, b.x2),
                this.y2 = Math.max(a.y2, b.y2));
    }
}

class DrawableRect extends Rectangle {

    protected javafx.scene.paint.Color outColor; // это поле служит для задания цвета границы прямоугольника

    DrawableRect(Rectangle rectangle, javafx.scene.paint.Color outColor) {
        x1 = rectangle.x1;
        y1 = rectangle.y1;
        height = rectangle.height;
        width = rectangle.width;
        this.outColor = outColor;
    }

    public javafx.scene.shape.Rectangle draw(){
        javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(this.x1, this.y1,
                this.height, this.width);
        rectangle.setStroke(outColor);
        return rectangle;
    }

}

class ColoredRect extends DrawableRect {
    protected javafx.scene.paint.Color inColor;

    ColoredRect(Rectangle rectangle, javafx.scene.paint.Color outColor, javafx.scene.paint.Color inColor) {
        super(rectangle, outColor);
        this.inColor = inColor;
    }

    @Override
    public javafx.scene.shape.Rectangle draw(){
//        Setting color to the circle
        javafx.scene.shape.Rectangle rectangle = new javafx.scene.shape.Rectangle(this.x1, this.y1,
                this.height, this.width);
        //Setting color to the circle
        rectangle.setFill(inColor);
        //Setting the stroke width
        rectangle.setStrokeWidth(3);
        //Setting color to the stroke
        rectangle.setStroke(outColor);

        return rectangle;
    }
}