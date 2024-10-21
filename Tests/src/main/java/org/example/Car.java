package org.example;

public class Car {
    private double battery;
    private double fuelAmount;
    private double fuelCapacity;
    private double fuelConsumption;
    private double distanceTravelled;

    public Car(double battery, double fuelAmount, double fuelCapacity, double fuelConsumption, double distanceTravelled) {
        this.battery = battery;
        this.fuelAmount = fuelAmount;
        this.fuelCapacity = fuelCapacity;
        this.fuelConsumption = fuelConsumption;
        this.distanceTravelled = distanceTravelled;
    }

    public boolean turnOn(){
        return fuelAmount > 15 && battery > 15;
    }

    public void drive(double kilometers) {
        double fuelNeeded = kilometers*fuelConsumption;
        if (turnOn()) {
            if (fuelAmount >= fuelNeeded) {
                distanceTravelled += kilometers;
                fuelAmount -= fuelNeeded;
                System.out.println("Car drove " + kilometers + " kilometers.");
            } else {
                System.out.println("Nie starczy paliwa");
            }
        }
    }

    public boolean refuel(double amount) {
        if (amount > 0) {
            if (fuelAmount + amount <= fuelCapacity) {
                fuelAmount += amount;
                return true;
            } else {
                fuelAmount=fuelCapacity;
                return true;
            }
        } else {
            return false;
        }
    }
}
