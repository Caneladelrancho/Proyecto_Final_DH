package com.dh.Proyecto.Final_BackEnd.model.dto;

import java.util.List;

public class RoomDto {

    private String roomNumber;
    private Double cost;
    private Boolean availability;
    private List<ImageDto> images;

    public RoomDto() {
    }

    public RoomDto(String roomNumber, Double cost, Boolean availability, List<ImageDto> images) {
        this.roomNumber = roomNumber;
        this.cost = cost;
        this.availability = availability;
        this.images = images;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public List<ImageDto> getImages() {
        return images;
    }

    public void setImages(List<ImageDto> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "RoomDto{" +
                "roomNumber='" + roomNumber + '\'' +
                ", cost=" + cost +
                ", availability=" + availability +
                ", images=" + images +
                '}';
    }
}


