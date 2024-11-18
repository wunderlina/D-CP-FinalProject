package state;

public class GearNeutral implements Gear {
    @Override
    public Gear nextGear(UserInput ui) {
        return null;
    }

    @Override
    public void resolveBehavior(UserInput ui, Car car) {

    }
}
