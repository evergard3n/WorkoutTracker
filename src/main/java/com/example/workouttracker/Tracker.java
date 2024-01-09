package com.example.workouttracker;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Tracker {
    protected String path;
    //private File data = new File(path);
    protected File data;
    protected boolean firstDay = false;
    protected int counter = 0;
    protected LocalDate lastDate;
    public Tracker() {}
    public Tracker(File data) {
        //getLastDate();
        this.data = data;
    }
    public int getCounter() {
        getLastDate();
        return counter;
    }
    void getLastDate() {
        try (Scanner scanner = new Scanner(data)) {
            // Read the entire line from the file

            if(data.length()!=0) {
                firstDay = false;
                counter = scanner.nextInt();
                scanner.nextLine();
                String line = scanner.nextLine();
                // Extract the date part (assuming the date is always at the end of the line)
                String dateString = line.substring(line.lastIndexOf(" ") + 1);

                // Specify the format for the date
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");

                // Parse the date from the extracted string
                lastDate = LocalDate.parse(dateString, formatter);
            }
            else {
                firstDay = true;
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

    }
    void handleSunday() {
        LocalDate now = LocalDate.now();

            if(lastDate.getDayOfWeek() == DayOfWeek.SATURDAY) {
                counter++;
            }
            else {
                counter =1;
            }

    }
    public enum options {
        ALREADY, CONSECUTIVE, SUNDAY,SKIPPED;
    }
    public options changeCounter() {
        // get the last date
        getLastDate();
        //check if its first day or not
        if(!firstDay) {
            if (lastDate.equals(LocalDate.now().minusDays(1))) {
                counter++;
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
        else {
            counter++;
            //information.setText("Congratulations!");
            updateData();
            return options.CONSECUTIVE;
        }
        //dayCounter.setText(String.valueOf(counter));
    }

    protected void updateData() {
        lastDate = LocalDate.now();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(data, false))) {
            // The second parameter 'true' in FileWriter constructor appends to the file if it already exists
            writer.write(String.valueOf(counter));
            writer.newLine(); // Add a new line for better readability or if you plan to write more data
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
            String formattedDate = lastDate.format(formatter);
            writer.write("Last workout: " + formattedDate);
            System.out.println("Date has been written to the file.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }
}
