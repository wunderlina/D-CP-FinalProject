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
            car.changeSpeed((int) (-1*car.getSpeed()*0.1));
            if (currentSpeed <= 1){
                car.changeSpeed(-1*currentSpeed);
                car.setStalled(false);
            }
        }
        else if (currentSpeed < 200 && ui.getKeyPressed("SPACE")) {
            car.changeSpeed(2);
        }
        else {
            car.changeSpeed(-1);
        }
    }

    @Override
    public int getGear() {
        return 4;
    }
}
