package state;

public interface Gear {
    Gear nextGear(UserInput ui);
    void resolveBehavior(UserInput ui, Car car);
    int getGear();
}
