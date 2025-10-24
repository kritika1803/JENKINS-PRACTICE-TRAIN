package com.klef.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klef.dev.entity.Train;
import com.klef.dev.service.TrainService;

@RestController
@RequestMapping("/trainapi/")
@CrossOrigin(origins = "*")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/")
    public String home() {
        return "Train Management API - Docker Full Stack Project";
    }

    // Add Train
    @PostMapping("/add")
    public ResponseEntity<Train> addTrain(@RequestBody Train train) {
        Train savedTrain = trainService.addTrain(train);
        return new ResponseEntity<>(savedTrain, HttpStatus.CREATED);
    }

    // Get all Trains
    @GetMapping("/all")
    public ResponseEntity<List<Train>> getAllTrains() {
        List<Train> trains = trainService.getAllTrains();
        return new ResponseEntity<>(trains, HttpStatus.OK);
    }

    // Get Train by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTrainById(@PathVariable int id) {
        Train train = trainService.getTrainById(id);
        if (train != null) {
            return new ResponseEntity<>(train, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Train with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Update Train details
    @PutMapping("/update")
    public ResponseEntity<?> updateTrain(@RequestBody Train train) {
        Train existing = trainService.getTrainById(train.getId());
        if (existing != null) {
            Train updatedTrain = trainService.updateTrain(train);
            return new ResponseEntity<>(updatedTrain, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot update. Train with ID " + train.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // Delete Train by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTrain(@PathVariable int id) {
        Train existing = trainService.getTrainById(id);
        if (existing != null) {
            trainService.deleteTrainById(id);
            return new ResponseEntity<>("Train with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. Train with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
