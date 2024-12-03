package state;

import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages User Input
 */
public class UserInput {
    private Map<String, Input> keyInput;
    private Mouse mouse;

    public UserInput(Pane pane, String[] monitoredKeys){
        keyInput = getKeyInput(pane, monitoredKeys);
        mouse = getMouse(pane);
    }

    private Map<String, Input> getKeyInput(Pane root, String[] monitoredKeys){
        Map<String, Input> inputs = new HashMap<>();
        for (String s:monitoredKeys){
            inputs.put(s, new Input());
        }
        root.requestFocus();
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(inputs.containsKey(keyEvent.getCode().toString())){
                    inputs.get(keyEvent.getCode().toString()).pressed = true;
                    inputs.get(keyEvent.getCode().toString()).ready = true;
                }
            }
        });
        root.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(inputs.containsKey(keyEvent.getCode().toString())){
                    inputs.get(keyEvent.getCode().toString()).pressed = false;
                }
            }
        });
        return inputs;
    }

    private Mouse getMouse(Pane root){
        Mouse mouse = new Mouse();
        root.requestFocus();
        root.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouse.position = new Point2D(mouseEvent.getX(), mouseEvent.getY());
            }
        });
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouse.mouseDown = true;
            }
        });
        root.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mouse.mouseDown = false;
            }
        });
        return mouse;
    }

    /**
     * Determines if the given key is currently being pressed
     * @param key the given key
     * @return whether the given key is currently pressed
     */
    public boolean getKeyPressed(String key){
        return keyInput.get(key).pressed;
    }

    /**
     * Determines if the given key is currently being tapped
     * @param key the given key
     * @return whether the given key is currently being tapped
     */
    public boolean checkTapped(String key){
        boolean r = keyInput.get(key).pressed && keyInput.get(key).ready;
        keyInput.get(key).ready = false;
        return r;
    }

    public boolean getMousePressed(){
        return mouse.mouseDown;
    }

    public double getMouseX(){
        return mouse.position.getX();
    }

    public double getMouseY(){
        return mouse.position.getY();
    }

    private static class Input {
        private boolean pressed;
        private boolean ready;
        private Input(){
            pressed = false;
            ready = true;
        }
    }

    private static class Mouse {
        private Point2D position;
        private boolean mouseDown;
        private Mouse(){
            position = new Point2D(0,0);
            mouseDown = false;
        }
    }
}
