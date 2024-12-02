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

    }

    @Override
    public int getGear() {
        return 2;
    }
}
