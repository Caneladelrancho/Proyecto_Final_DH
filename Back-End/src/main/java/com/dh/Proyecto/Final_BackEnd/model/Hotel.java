package com.dh.Proyecto.Final_BackEnd.model;

import jakarta.persistence.*;
import java.util.List;



@Entity
@Table(name = "hotels")

public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //mirar si es necesario EAGER o LAZY
    @JoinColumn (name = "address_id")
    private Address address;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "image_id")
    private Image image;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Room> rooms;
    @Column(name = "description")
    private String description;
    public Hotel() {
    }

    public Hotel(Long id, String name, Address address, Image image, List<Room> rooms, String description) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.image = image;
        this.rooms = rooms;
        this.description = description;
    }

    public Hotel(String name, Address address, Image image, List<Room> rooms, String description) {
        this.name = name;
        this.address = address;
        this.image = image;
        this.rooms = rooms;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
