package com.dh.Proyecto.Final_BackEnd.model.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class RoomDto {

    private String roomNumber;
    private Double cost;
    private Boolean availability;
    private List<MultipartFile> images;
    private List<String> imagesUrl;

    public RoomDto() {
    }

    public RoomDto(String roomNumber, Double cost, Boolean availability, List<MultipartFile> images, List<String> imagesUrl) {
        this.roomNumber = roomNumber;
        this.cost = cost;
        this.availability = availability;
        this.images = images;
        this.imagesUrl = imagesUrl;
    }

    public RoomDto(String roomNumber, Double cost, Boolean availability) {
        this.roomNumber = roomNumber;
        this.cost = cost;
        this.availability = availability;
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

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
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


