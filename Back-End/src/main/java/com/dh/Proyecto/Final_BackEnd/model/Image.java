package com.dh.Proyecto.Final_BackEnd.model;

import jakarta.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long Id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "image_url")
    private String imageUrl;
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    public Image() {
    }

    public Image(Long id, String name, String description, String imageUrl, Room room) {
        Id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.room = room;

    }

    public Image(String name, String description, String imageUrl, Room room) {//saveimage retorne este constructor
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.room = room;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }


}
