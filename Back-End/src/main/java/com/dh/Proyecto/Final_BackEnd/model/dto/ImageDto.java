package com.dh.Proyecto.Final_BackEnd.model.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ImageDto {

    private String name;

    private String description;

    private String imageUrl; //agregado 11 de noviembre

    private List<MultipartFile> images;

    public ImageDto() {
    }

    public ImageDto(String name, String description, String imageUrl, List<MultipartFile> images) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.images = images;
    }

    public ImageDto(List<MultipartFile> images) {
        this.images = images;
    }

    public ImageDto(String name, String description, List<MultipartFile> images) {
        this.name = name;
        this.description = description;
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<MultipartFile> getImages() {
        return images;
    }

    public void setImages(List<MultipartFile> images) {
        this.images = images;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
