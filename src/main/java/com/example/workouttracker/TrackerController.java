package com.example.workouttracker;

import java.io.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TrackerController implements Initializable {
    private int counter = 0;
    //private final File data = new File("src/resources/data.txt");
    @FXML
    private Label dayCounter, information;
    @FXML
    private Button confirmation;
    private LocalDate lastDate;
    private boolean firstDay;
    private Tracker fitnessTrack = new Tracker();
    public TrackerController() {
        counter = 0;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //getLastDate();
        counter = fitnessTrack.getCounter();
        dayCounter.setText(String.valueOf(counter));
        confirmation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                int track = fitnessTrack.changeCounter();
                switch (track) {
                    case 1-> {
                        information.setText("You already worked out today!");
                    }
                    case 2, 4 -> {
                        information.setText("Congratulations!");
                    }
                }
                counter = fitnessTrack.getCounter();
                dayCounter.setText(String.valueOf(counter));
            }
        });
    }

//    private void getLastDate() {
//        try (Scanner scanner = new Scanner(data)) {
//            // Read the entire line from the file
//
//            if(data.length()!=0) {
//                firstDay = false;
//                counter = scanner.nextInt();
//                scanner.nextLine();
//                String line = scanner.nextLine();
//                // Extract the date part (assuming the date is always at the end of the line)
//                String dateString = line.substring(line.lastIndexOf(" ") + 1);
//
//                // Specify the format for the date
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
//
//                // Parse the date from the extracted string
//                lastDate = LocalDate.parse(dateString, formatter);
//            }
//            else {
//                firstDay = true;
//            }
//        } catch (FileNotFoundException e) {
//            System.err.println("File not found: " + e.getMessage());
//        }
//
//    }
//
//    private void changeCounter() {
//        getLastDate();
//        if(!firstDay) {
//            //dayCounter.setText(String.valueOf(counter));
//            if (lastDate.equals(LocalDate.now())) {
//                information.setText("You already worked out today!");
//                System.out.println("You already worked out today!");
//            } else if (lastDate.isBefore(LocalDate.now())) {
//                counter++;
//                information.setText("Congratulations!");
//                System.out.println("Congratulations!");
//                //dayCounter.setText(String.valueOf(counter));
//                updateData();
//            } else {
//                counter = 1;
//                //dayCounter.setText(String.valueOf(counter));
//                updateData();
//            }
//        }
//        else {
//            counter++;
//            information.setText("Congratulations!");
//            updateData();
//        }
//        dayCounter.setText(String.valueOf(counter));
//    }
//
//    private void updateData() {
//        lastDate = LocalDate.now();
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(data, false))) {
//            // The second parameter 'true' in FileWriter constructor appends to the file if it already exists
//            writer.write(String.valueOf(counter));
//            writer.newLine(); // Add a new line for better readability or if you plan to write more data
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/M/d");
//            String formattedDate = lastDate.format(formatter);
//            writer.write("Last workout: " + formattedDate);
//            System.out.println("Date has been written to the file.");
//        } catch (IOException e) {
//            System.err.println("Error writing to the file: " + e.getMessage());
//        }
//    }

}
