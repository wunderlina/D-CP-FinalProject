package state;

public class GearReverse implements Gear {

    @Override
    public Gear nextGear(UserInput ui) {
        Gear gear = this;
        if (ui.checkTapped("UP")) {
            gear = new GearNeutral();
        }
        if (ui.checkTapped("DOWN")) {
            gear = new GearPark();
        }
        return gear;
    }

    @Override
    public void resolveBehavior(UserInput ui, Car car) {
        if(!car.getStalled() && ui.getKeyPressed("SPACE")){
            if(car.getSpeed() > -30){
                car.changeSpeed(-1);
            }
        } else if (car.getSpeed() != 0) {
            car.changeSpeed(-1*car.getSpeed()/Math.abs(car.getSpeed()));
        }
    }

    @Override
    public int getGear() {
        return 1;
    }
}
