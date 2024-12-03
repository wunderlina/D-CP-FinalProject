package state;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class GuiImage {
    private Point2D anchorPoint;
    private Point2D relativePosition;
    private boolean visible;
    private Image[] image;
    private double size;
    private int costume;

    public GuiImage(Point2D anchor, Point2D pos, double size, String[] paths) {
        anchorPoint = anchor;
        relativePosition = pos;
        visible = true;
        if(paths == null){
            this.image = null;
        } else {
            image = new Image[paths.length];
            try{
                for (int i = 0; i < paths.length; i++){
                    image[i] = new Image(new FileInputStream(Path.of(paths[i]).toFile()));
                }

            } catch (IOException e){
                System.out.println("IOException: "+e);
            }
        }
        this.size = size;
        this.costume = 0;
    }

    public void render(GraphicsContext pen, double zoom){
        if(visible){
            double frameWidth = pen.getCanvas().getWidth();
            double frameHeight = pen.getCanvas().getHeight();
            pen.drawImage(image[costume], frameWidth*anchorPoint.getX() + (relativePosition.getX())*zoom, frameHeight*anchorPoint.getY() + (relativePosition.getY())*zoom, image[costume].getWidth()*size*zoom, image[costume].getHeight()*size*zoom);
        }
    }

    public boolean touchingMouse(GraphicsContext pen, double zoom, UserInput userInput){
        if(visible){
            double frameWidth = pen.getCanvas().getWidth();
            double frameHeight = pen.getCanvas().getHeight();
            double topBorder = frameHeight*anchorPoint.getY() + (relativePosition.getY())*zoom;
            double bottomBorder = frameHeight*anchorPoint.getY() + (relativePosition.getY())*zoom + image[costume].getHeight()*size*zoom;
            double leftBorder = frameWidth*anchorPoint.getX() + (relativePosition.getX())*zoom;
            double rightBorder = frameWidth*anchorPoint.getX() + (relativePosition.getX())*zoom + image[costume].getWidth()*size*zoom;
            return userInput.getMouseX() < rightBorder && userInput.getMouseX() > leftBorder && userInput.getMouseY() > topBorder && userInput.getMouseY() < bottomBorder;
        }
        return false;
    }

    public void setCostume(int cost){
        costume = cost;
    }

    public void setCostumes(Image[] costumes){
        image = costumes;
    }

    public int getCostume(){
        return costume;
    }
}
