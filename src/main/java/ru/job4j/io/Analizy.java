package ru.job4j.io;

import java.io.*;
import java.util.stream.Collectors;

public class Analizy {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            try (FileOutputStream out = new FileOutputStream(target)) {
                boolean flag = false;
                String s;
                while ((s = in.readLine()) != null) {
                    String[] rsl = s.split(" ");
                    if ("400".equals(rsl[0]) || "500".equals(rsl[0])) {
                        if (!flag) {
                            out.write(rsl[1].getBytes());
                            out.write(";".getBytes());
                        }
                        flag = true;
                    }
                    if (("200".equals(rsl[0]) || "300".equals(rsl[0])) && flag) {
                        out.write(rsl[1].getBytes());
                        flag = false;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String source = "Source.txt";
        String target = "Target.txt";
        Analizy test = new Analizy();
        test.unavailable(source, target);
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
