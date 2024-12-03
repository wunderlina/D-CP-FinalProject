package state;

public class GearReverse implements Gear {
    @Override
    public Gear nextGear(UserInput ui) {
        return this;
    }

    @Override
    public void resolveBehavior(UserInput ui, Car car) {
        car.changeSpeed(-1);
    }

    @Override
    public int getGear() {
        return 1;
    }
}
