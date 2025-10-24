package com.klef.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.dev.entity.Train;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {
    
    // Custom finder methods
    Train findByContactEmail(String contactEmail);
    Train findByContactNumber(String contactNumber);
}
