package com.klef.dev.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "train_table")
public class Train {

    @Id
    @Column(name = "train_id")
    private int id;

    @Column(name = "train_name", nullable = false, length = 100)
    private String name;

    @Column(name = "train_type", nullable = false, length = 50)
    private String type; // e.g., Express, Passenger, Superfast

    @Column(name = "train_source", nullable = false, length = 100)
    private String source;

    @Column(name = "train_destination", nullable = false, length = 100)
    private String destination;

    @Column(name = "train_total_coaches", nullable = false)
    private int totalCoaches;

    @Column(name = "train_capacity", nullable = false)
    private int capacity; // total passengers it can carry

    @Column(name = "train_fare", nullable = false)
    private double fare;

    @Column(name = "train_contact_email", nullable = false, unique = true, length = 100)
    private String contactEmail;

    @Column(name = "train_contact_number", nullable = false, unique = true, length = 20)
    private String contactNumber;

    @Column(name = "train_schedule", length = 100)
    private String schedule; // e.g., Daily, Weekly

    @Column(name = "train_status", length = 50)
    private String status; // e.g., Running, Delayed, Cancelled

    @Column(name = "train_rating")
    private double rating; // average passenger rating

    @Column(name = "train_description", length = 500)
    private String description;

    @Column(name = "train_date_of_introduction")
    private LocalDate dateOfIntroduction;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }
    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotalCoaches() {
        return totalCoaches;
    }
    public void setTotalCoaches(int totalCoaches) {
        this.totalCoaches = totalCoaches;
    }

    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getFare() {
        return fare;
    }
    public void setFare(double fare) {
        this.fare = fare;
    }

    public String getContactEmail() {
        return contactEmail;
    }
    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getSchedule() {
        return schedule;
    }
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public double getRating() {
        return rating;
    }
    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfIntroduction() {
        return dateOfIntroduction;
    }
    public void setDateOfIntroduction(LocalDate dateOfIntroduction) {
        this.dateOfIntroduction = dateOfIntroduction;
    }

    @Override
    public String toString() {
        return "Train [id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", source=" + source +
                ", destination=" + destination +
                ", totalCoaches=" + totalCoaches +
                ", capacity=" + capacity +
                ", fare=" + fare +
                ", contactEmail=" + contactEmail +
                ", contactNumber=" + contactNumber +
                ", schedule=" + schedule +
                ", status=" + status +
                ", rating=" + rating +
                ", description=" + description +
                ", dateOfIntroduction=" + dateOfIntroduction + "]";
    }
}
