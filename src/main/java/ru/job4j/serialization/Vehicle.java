package ru.job4j.serialization;

import java.util.Arrays;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Vehicle {
    private boolean isExpensive;
    private int numOfWheels;
    private String brand;
    private String[] options;
    private Driver driver;

    public Vehicle(boolean isExpensive, int numOfWheels, String brand, String[] options, Driver driver) {
        this.isExpensive = isExpensive;
        this.numOfWheels = numOfWheels;
        this.brand = brand;
        this.options = options;
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Vehicle{"
                + "isExpensive=" + isExpensive
                + ", numOfWheels=" + numOfWheels
                + ", brand='" + brand + '\''
                + ", options=" + Arrays.toString(options)
                + ", driver=" + driver
                + '}';
    }

    public static void main(String[] args) {
        String[] arr = {"Music", "Sport"};
        Vehicle car = new Vehicle(true, 4, "Porsche", arr, new Driver(10));
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));
        final String personJson =
                "{"
                        + "\"isExpensive\":true,"
                        + "\"numOfWheels\":4,"
                        + "\"brand\":Porsche,"
                        + "\"driver\":"
                        + "{"
                        + "\"yearsOfExperience\":\"10\""
                        + "},"
                        + "\"options\":"
                        + "[\"Music\", \"Sport\"]"
                        + "}";
        final Vehicle vehicleMod = gson.fromJson(personJson, Vehicle.class);
        System.out.println(vehicleMod);
    }
}
