package state;

public class GearHigh implements Gear {
    @Override
    public Gear nextGear(UserInput ui) {
        Gear gear = this;
        if (ui.checkTapped("DOWN")) {
            gear = new GearLow();
        }
        return gear;
    }

    @Override
    public void resolveBehavior(UserInput ui, Car car) {
//stall when speed below 40
        int currentSpeed = car.getSpeed();
        if(ui.getKeyPressed("SPACE") && !car.getStalled()){
            if (currentSpeed < 30) {
                car.setStalled(true);
            }
            if (currentSpeed < 200){
                car.changeSpeed(2);
            }
        } else {
            if(car.getSpeed() != 0){
                car.changeSpeed(-1*car.getSpeed()/Math.abs(car.getSpeed()));
            }
        }
    }

    @Override
    public int getGear() {
        return 4;
    }
}
