package ru.job4j.serialization;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        JSONObject jsonDriver = new JSONObject("{\"yearsOfExperience\":\"10\"}");

        List<String> list = new ArrayList<>();
        list.add("Music");
        list.add("Sport");
        JSONArray jsonOptions = new JSONArray(list);

        String[] arr = {"Music", "Sport"};
        Vehicle car = new Vehicle(true, 4, "Porsche", arr, new Driver(10));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("isExpensive", car.isExpensive());
        jsonObject.put("numOfWheels", car.getNumOfWheels());
        jsonObject.put("driver", jsonDriver);
        jsonObject.put("options", jsonOptions);
        System.out.println(new JSONObject(car).toString());
    }
}
