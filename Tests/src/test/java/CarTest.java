import org.example.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarTest {

    @Test
    public void carTurnsOnWhenItHasButteryAndFuel() {
        Car car = new Car(30,50,50,0.8,0);

        boolean result=car.turnOn();

        assertTrue(result);
    }

    @Test
    public void carDontTurnOnWhenDontHaveButtery() {
        Car car = new Car(0,50,50,0.8,0);

        boolean result=car.turnOn();

        assertFalse(result);
    }

    @Test
    public void carDontTurnOnWhenDontHaveFuel() {
        Car car = new Car(30,0,50,0.8,0);

        boolean result=car.turnOn();

        assertFalse(result);
    }

    @Test
    public void carDontTurnOnWhenDontHaveFuelAndBattery() {
        Car car = new Car(0,0,50,0.8,0);

        boolean result=car.turnOn();

        assertFalse(result);
    }

    @Test
    public void carFuel() {
        Car car = new Car(0,0,50,0.8,0);

        boolean result=car.turnOn();

        assertFalse(result);
    }
}
