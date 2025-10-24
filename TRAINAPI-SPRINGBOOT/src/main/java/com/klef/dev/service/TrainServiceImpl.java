package com.klef.dev.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.dev.entity.Train;
import com.klef.dev.repository.TrainRepository;

@Service
public class TrainServiceImpl implements TrainService {

    @Autowired
    private TrainRepository trainRepository;

    @Override
    public Train addTrain(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    @Override
    public Train getTrainById(int id) {
        Optional<Train> opt = trainRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Train updateTrain(Train train) {
        return trainRepository.save(train);
    }

    @Override
    public void deleteTrainById(int id) {
        trainRepository.deleteById(id);
    }
}
