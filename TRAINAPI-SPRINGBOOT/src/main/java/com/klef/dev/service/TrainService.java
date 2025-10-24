package com.klef.dev.service;

import java.util.List;
import com.klef.dev.entity.Train;

public interface TrainService {
    
    Train addTrain(Train train);
    List<Train> getAllTrains();
    Train getTrainById(int id);
    Train updateTrain(Train train);
    void deleteTrainById(int id);
}
