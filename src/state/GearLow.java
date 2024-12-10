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
//max velocity 60
        int currentSpeed = car.getSpeed();
        if(!car.getStalled() && ui.getKeyPressed("SPACE")){
            if(car.getSpeed() < 60){
                car.changeSpeed(2);
            } else {
                car.changeSpeed(-1*car.getSpeed()/Math.abs(car.getSpeed()));
            }
        } else {
            if(car.getSpeed() != 0){
                car.changeSpeed(-1*car.getSpeed()/Math.abs(car.getSpeed()));
            }
        }
    }

    @Override
    public int getGear() {
        return 3;
    }
}
