package state;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;

public class GuiImage extends GuiElement {
    private Image[] image;
    private double size;
    private int costume;
    public GuiImage(Point anchor, Point pos, double size, String[] paths) {
        super(anchor, pos);
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
            pen.drawImage(image[costume], frameWidth*anchorPoint.x + (relativePosition.x)*zoom, frameHeight*anchorPoint.y + (relativePosition.y)*zoom, image[costume].getWidth()*size*zoom, image[costume].getHeight()*size*zoom);
        }
    }
    public boolean touchingMouse(GraphicsContext pen, double zoom, UserInput userInput){
        if(visible){
            double frameWidth = pen.getCanvas().getWidth();
            double frameHeight = pen.getCanvas().getHeight();
            double topBorder = frameHeight*anchorPoint.y + (relativePosition.y)*zoom;
            double bottomBorder = frameHeight*anchorPoint.y + (relativePosition.y)*zoom + image[costume].getHeight()*size*zoom;
            double leftBorder = frameWidth*anchorPoint.x + (relativePosition.x)*zoom;
            double rightBorder = frameWidth*anchorPoint.x + (relativePosition.x)*zoom + image[costume].getWidth()*size*zoom;
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
