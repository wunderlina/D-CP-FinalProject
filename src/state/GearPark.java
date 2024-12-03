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
        if (currentSpeed > 0) {
            car.changeSpeed(--currentSpeed);
        }
    }

    @Override
    public int getGear() {
        return 0;
    }
}
