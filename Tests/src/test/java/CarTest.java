import org.example.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    public void carFuelWithOkAmount() {
        Car car = new Car(15,20,50,0.8,0);

        boolean result = car.refuel(20);

        assertTrue(result);
        assertEquals(40, car.getFuelAmount());
    }

    @Test
    public void carFuelWithTooBigAmount() {
        Car car = new Car(15,20,50,0.8,0);

        boolean result=car.refuel(40);

        assertTrue(result);
        assertEquals(car.getFuelCapacity(), car.getFuelAmount());
    }

    @Test
    public void carFuelWithBadAmount() {
        Car car = new Car(15,20,50,0.8,0);

        boolean result=car.refuel(0);

        assertFalse(result);
        assertEquals(20, car.getFuelAmount());
    }
}
