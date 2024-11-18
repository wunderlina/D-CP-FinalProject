package state;

public class Car {
    private Gear gear;
    private int speed;
    private boolean stalled;
    private UserInput userInput;

    public Car(UserInput ui){

    }
    public void changeSpeed(int increment){
        speed += increment;
    }
    public void setStalled(boolean bool){
        stalled = bool;
    }
    public int getSpeed(){
        return speed;
    }
    public boolean getStalled(){
        return stalled;
    }
    private void shiftGears(){

    }
    public void update(){

    }
}
