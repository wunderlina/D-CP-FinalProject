package state;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Driver extends Application {
    private static final double DEGREE_SPEED_RATIO = 0.01963495;
    private static final double ANGLE_OFFSET = (5*Math.PI/4);
    private static final int WINDOW_WIDTH = 900;
    private static final int WINDOW_HEIGHT = 600;
    private final String data = "data/";
    private static boolean run = true;
    private static GraphicsContext pen;
    private static UserInput userInput;
    private static Car car;
    private static GuiImage[] guiElements;
    //private static Graphics

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        Canvas window = new Canvas();
        window.widthProperty().bind(root.widthProperty());
        window.heightProperty().bind(root.heightProperty());
        root.getChildren().add(window);
        stage.setScene(new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT));
        stage.setTitle("Final Project");
        stage.show();

        guiElements = new GuiImage[] {
                new GuiImage(new Point2D(0, 0), new Point2D(100, 100), 1, new String[] {data + "speed.png"}),
                new GuiImage(new Point2D(0, 0), new Point2D(600, 100), 1, new String[] {data + "Gas.png", data + "GasPressed.png"}),
                new GuiImage(new Point2D(0, 0), new Point2D(600, 250), 1, new String[] {data + "GearUp.png", data + "GearUpPressed.png"}),
                new GuiImage(new Point2D(0, 0), new Point2D(600, 400), 1, new String[] {data + "GearDown.png", data + "GearDownPressed.png"}),
                new GuiImage(new Point2D(0, 0), new Point2D(240, 385), 1, new String[] {data + "park.png", data + "reverse.png", data + "neutral.png", data + "low.png", data + "high.png"}),
                new GuiImage(new Point2D(0, 0), new Point2D(450, 450), 0.75, new String[] {data + "keyOff.png", data + "keyOn.png"})
        };

        pen = window.getGraphicsContext2D();
        String[] monitoredKeys = {"SPACE", "UP", "DOWN"};
        userInput = new UserInput(root, monitoredKeys);

        car = new Car(userInput);

        run();
    }
    private static void run(){
        Thread displayThread = new Thread(() ->{
            while(run){
                display();
            }
        });
        Thread updateThread = new Thread(()->{
            while(run){
                update();
            }
        });
        displayThread.start();
        updateThread.start();
    }
    private static void display() {
        pen.clearRect(0,0,pen.getCanvas().getWidth(), pen.getCanvas().getHeight());
        for(GuiImage gi : guiElements){
            gi.render(pen,1);
        }

        double angle = Math.abs(car.getSpeed())*DEGREE_SPEED_RATIO - ANGLE_OFFSET;
        double length = 150;

        pen.setLineWidth(3);
        pen.setStroke(Color.RED);
        pen.strokeLine(306, 306, 306 + Math.cos(angle)*length, 306 + Math.sin(angle)*length);

        wait(50);
    }
    private static void update(){
        if(userInput.getKeyPressed("SPACE")){
            guiElements[1].setCostume(1);
        }else{
            guiElements[1].setCostume(0);
        }
        if(userInput.getKeyPressed("UP")){
            guiElements[2].setCostume(1);
        }else{
            guiElements[2].setCostume(0);
        }
        if(userInput.getKeyPressed("DOWN")){
            guiElements[3].setCostume(1);
        }else{
            guiElements[3].setCostume(0);
        }

        guiElements[4].setCostume(car.getGear());

        if(car.getStalled()){
            guiElements[5].setCostume(0);
        }else{
            guiElements[5].setCostume(1);
        }
        if(userInput.getMousePressed() && guiElements[5].touchingMouse(pen, 1, userInput)){
            car.setStalled(false);
        }

        car.update();
        wait(50);
    }
    public static void wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException: "+e);
        }
    }
    private static void locatorTool(){
        if(userInput.getMousePressed()){
            double x = userInput.getMouseX();
            double y = userInput.getMouseY();
            System.out.printf("(%f, %f)\n", x, y);
        }
    }
}
