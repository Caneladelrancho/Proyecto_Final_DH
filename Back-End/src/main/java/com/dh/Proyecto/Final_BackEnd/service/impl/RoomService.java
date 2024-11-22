package com.dh.Proyecto.Final_BackEnd.service.impl;

import com.dh.Proyecto.Final_BackEnd.model.Image;
import com.dh.Proyecto.Final_BackEnd.model.Room;
import com.dh.Proyecto.Final_BackEnd.model.dto.ImageDto;
import com.dh.Proyecto.Final_BackEnd.model.dto.RoomDto;
import com.dh.Proyecto.Final_BackEnd.repository.IImageRepository;
import com.dh.Proyecto.Final_BackEnd.repository.IRoomRepository;
import com.dh.Proyecto.Final_BackEnd.service.IImageService;
import com.dh.Proyecto.Final_BackEnd.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class RoomService implements IRoomService {

    //Inyectar dependencias
    @Autowired
    private IRoomRepository roomRepository;
    @Autowired
    private IImageService imageService;

    //GUARDAR O AGREGAR UN ROOM CON IMÁGENES
    @Override
    public RoomDto saveRoom(RoomDto roomDto) throws IOException {

        try {
            //Crear una entidad room y desde RoomDto se asignan los valores, pero sin las imágenes
            Room room = new Room();
            room.setRoomNumber(roomDto.getRoomNumber());
            room.setCost(roomDto.getCost());
            room.setAvailability(roomDto.getAvailability());

            Room roomSaved = roomRepository.save(room); //Guardar Room en la BD

            //Manejar varias imágenes si están presentes
            List<Image> images = new ArrayList<>();

            if (roomDto.getImages() != null && !roomDto.getImages().isEmpty()) {
                for (MultipartFile image : roomDto.getImages()) {

                    Image imageToSaveRoom = imageService.saveImage(image, roomSaved);
                    images.add(imageToSaveRoom);
                }
            }

            //Generar una instancia de RoomDTO
            RoomDto roomDtoToReturn = new RoomDto();
            roomDtoToReturn.setRoomNumber(room.getRoomNumber());
            roomDtoToReturn.setCost(room.getCost());
            roomDtoToReturn.setAvailability(room.getAvailability());

            //Convertir la lista de imágenes de Room a una lista de ImageDto
            List<String> imageUrlList = new ArrayList<>();
            for (Image image : images) {
                imageUrlList.add(image.getImageUrl());
            }

            //Manejar un imagedto que tenga la url de la imagen
            roomDtoToReturn.setImagesUrl(imageUrlList);//Asignar la lista de ImageDto a RoomDto
            System.out.println(roomDtoToReturn);
            return roomDtoToReturn;

        }catch (Exception e){
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Room> findAllRooms() {
        return null;
    }


    @Override
    public void delete(Long id) {
        roomRepository.deleteById(id);

    }

    private String getBaseUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }
}
