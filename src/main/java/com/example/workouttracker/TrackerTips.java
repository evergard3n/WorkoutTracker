package com.example.workouttracker;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class TrackerTips {
    private static LocalDate lcd = LocalDate.now();
    private static List<Workout> workouts = new ArrayList<>();
    public TrackerTips() {
        ini();
    }
    private static void ini() {
        try {
            Scanner scn = new Scanner(new File("src/resources/workouts.txt"));
            while(scn.hasNextLine()) {
                String line = scn.nextLine();
                String[] parts = line.split(" ");
                workouts.add(new Workout(parts[0],parts[1]));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static String print() {
        ini();
        StringBuilder sbc = new StringBuilder();
        if(lcd.getDayOfWeek()== DayOfWeek.MONDAY||lcd.getDayOfWeek()== DayOfWeek.THURSDAY) {
            sbc.append(workouts.get(0).toString()).append('\n').append(workouts.get(1).toString()).append("a");
        }
        if(lcd.getDayOfWeek()== DayOfWeek.TUESDAY||lcd.getDayOfWeek()== DayOfWeek.FRIDAY) {
            sbc.append(workouts.get(2).toString()).append('\n').append(workouts.get(3).toString());
        }
        if(lcd.getDayOfWeek()== DayOfWeek.WEDNESDAY||lcd.getDayOfWeek()== DayOfWeek.SATURDAY) {
            sbc.append(workouts.get(4).toString()).append('\n').append(workouts.get(5).toString());
        }
        if(lcd.getDayOfWeek()== DayOfWeek.SUNDAY) {
            sbc.append("Stretch bro");
        }
        return sbc.toString();
    }
}
