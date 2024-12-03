package state;

public class GearHigh implements Gear {
    @Override
    public Gear nextGear(UserInput ui) {
        Gear gear = this;
        if (ui.checkTapped("DOWN")) {
            gear = new GearLow();
        }
        return gear;
    }

    @Override
    public void resolveBehavior(UserInput ui, Car car) {
//stall when speed below 40
        int currentSpeed = car.getSpeed();
        if (currentSpeed < 40) {
            car.setStalled(true);
            car.changeSpeed((int) (-1*car.getSpeed()*0.3));
        }
        else if (currentSpeed < 200){
            if ( ui.getKeyPressed("SPACE")){
                car.changeSpeed(4);
            }
            else {
                car.changeSpeed(-2);
            }
        }
    }

    @Override
    public int getGear() {
        return 4;
    }
}
