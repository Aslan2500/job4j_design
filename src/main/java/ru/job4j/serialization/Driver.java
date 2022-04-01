package ru.job4j.serialization;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "driver")
public class Driver {

    @XmlAttribute
    private int yearsOfExperience;

    public Driver() {

    }

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
