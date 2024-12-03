package state;

public class GearPark implements Gear {

    @Override
    public Gear nextGear(UserInput ui) {
        Gear gear = this;
        if (ui.checkTapped("UP")) {
            gear = new GearReverse();
        }
        return gear;
    }

    @Override
    public void resolveBehavior(UserInput ui, Car car) {
        int currentSpeed = car.getSpeed();
        if (Math.abs(currentSpeed) < 4) {
            car.changeSpeed(-1*currentSpeed);
        } else {
            car.changeSpeed(-4*car.getSpeed()/Math.abs(car.getSpeed()));
        }
    }

    @Override
    public int getGear() {
        return 0;
    }
}
