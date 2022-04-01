package ru.job4j.serialization;

public class Driver {
    private int yearsOfExperience;

    public Driver(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String toString() {
        return "Driver{"
                + "yearsOfExpirience=" + yearsOfExperience
                + '}';
    }
}
