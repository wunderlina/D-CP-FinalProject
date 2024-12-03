package state;

public class GearLow implements Gear {
    @Override
    public Gear nextGear(UserInput ui) {
        Gear gear = this;
        if (ui.checkTapped("UP")) {
            gear = new GearHigh();
        }
        if (ui.checkTapped("DOWN")) {
            gear = new GearNeutral();
        }
        return gear;
    }

    @Override
    public void resolveBehavior(UserInput ui, Car car) {
//max velocity 50
        int currentSpeed = car.getSpeed();
        if (currentSpeed < 50 && ui.getKeyPressed("SPACE")){
            car.changeSpeed(8);
        }
        else {
            car.changeSpeed(-3);
        }
    }

    @Override
    public int getGear() {
        return 0;
    }
}
