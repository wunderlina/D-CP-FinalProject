package state;

public class Car {
    private Gear gear;
    private int speed;
    private boolean stalled;
    private final UserInput userInput;

    public Car(UserInput ui){
        userInput = ui;
        gear = new GearPark();
        stalled = true;
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
        this.gear = gear.nextGear(userInput);
    }

    public void update(){
        gear.resolveBehavior(userInput, this);
        shiftGears();
    }

    public int getGear(){
        return gear.getGear();
    }
}