package com.example.workouttracker;

import java.io.File;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class WorkoutTracker extends Tracker {
    public WorkoutTracker(File data) {
        super(data);
    }

    @Override
    public options changeCounter() {
        // get the last date
        getLastDate();
        //check if its first day or not
        if(!firstDay) {
            // check if already worked out today
            // gop: khong phai tap nua
            if (lastDate.equals(LocalDate.now())) {
                System.out.println("You already worked out today!");
                return options.ALREADY;
            }
            if (LocalDate.now().getDayOfWeek() == DayOfWeek.SUNDAY) {
                return options.SUNDAY;
            }
            // check if worked out yesterday
            if (lastDate.equals(LocalDate.now().minusDays(1))) {
                counter++;
                System.out.println("Congratulations!");
                updateData();
                return options.CONSECUTIVE;
            } else {
                // cac truong hop xay ra: hom nay la thu hai va lan cuoi la chu nhat. hom nay la thu hai
                // nhung lan cuoi khong phai chu nhat, hom nay khong phai thu hai
                if(LocalDate.now().getDayOfWeek()==DayOfWeek.MONDAY) {
                    handleSunday();
                    System.out.println("Congratulations!");
                    updateData();
                    return options.CONSECUTIVE;
                }
                else {
                    counter = 1;
                    //dayCounter.setText(String.valueOf(counter));
                    updateData();
                    return options.SKIPPED;
                }

            }
        }
        else {
            counter++;
            //information.setText("Congratulations!");
            updateData();
            return options.CONSECUTIVE;
        }
    }
}
