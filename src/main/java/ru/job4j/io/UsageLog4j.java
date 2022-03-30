package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        short age = 22;
        boolean isMale = true;
        char firstLetter = 'A';
        double height = 185;
        float grade = 7.5f;
        long phoneNumber = 123456L;
        int id = 54321;
        byte workingDays = 5;
        LOG.debug("User info height : {}, age : {}, is male ? : {}, first letter of the name : {}, "
                + "grade : {}, phone number : {}, id : {}, "
                + "number of working days : {}", height, age, isMale, firstLetter, grade, phoneNumber, id, workingDays);
    }
}
