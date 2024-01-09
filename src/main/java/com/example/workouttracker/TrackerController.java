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
    private int counter = 0, counter2 = 0;
    private final File data = new File("src/resources/data.txt");
    @FXML
    private Label dayCounter, information, workouttips, information2;

    @FXML
    private Button confirmation, settingsBtn;
    private LocalDate lastDate;
    private boolean firstDay;
    private final Tracker fitnessTrack = new WorkoutTracker(data);
    private final Tracker masterTrack = new Tracker(new File("src/resources/data2.txt"));
    public TrackerController() {
        counter = 0;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //getLastDate();
        counter = fitnessTrack.getCounter();
        counter2 = masterTrack.getCounter();
        dayCounter.setText(String.valueOf(counter)+" "+ String.valueOf(counter2));
        workouttips.setText(TrackerTips.print());
        //tips.setText(TrackerTips.print());
        confirmation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Tracker.options track = fitnessTrack.changeCounter();
                switch (track) {
                    case ALREADY-> {
                        information.setText("You already worked out today!");
                    }
                    case CONSECUTIVE -> {
                        information.setText("Congratulations!");
                    }
                    case SKIPPED -> {
                        information.setText("Make sure to practice tomorrow!");
                    }
                    case SUNDAY -> {
                        information.setText("It's Sunday, go outside!");
                        //information.setText(TrackerTips.print());

                    }
                }
                counter = fitnessTrack.getCounter();
                dayCounter.setText(String.valueOf(counter));
            }
        });
        settingsBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Tracker.options track = masterTrack.changeCounter();
                switch (track) {
                    case CONSECUTIVE -> {
                        information2.setText("Congratulations!");
                    }
                    case SKIPPED -> {
                        information2.setText("Make sure to practice tomorrow!");
                    }
                }
            }
        });
    }


}
