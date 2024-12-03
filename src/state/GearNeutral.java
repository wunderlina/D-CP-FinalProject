package state;

public class GearNeutral implements Gear {

    @Override
    public Gear nextGear(UserInput ui) {
        Gear gear = this;
        if (ui.checkTapped("UP")) {
            gear = new GearLow();
        }
        if (ui.checkTapped("DOWN")) {
            gear = new GearReverse();
        }
        return gear;
    }

    @Override
    public void resolveBehavior(UserInput ui, Car car) {
        car.changeSpeed(-1*car.getSpeed()/(Math.abs(car.getSpeed())+1));
    }

    @Override
    public int getGear() {
        return 2;
    }
}

