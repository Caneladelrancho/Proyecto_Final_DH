package com.dh.Proyecto.Final_BackEnd.service.impl;

import com.dh.Proyecto.Final_BackEnd.model.Image;
import com.dh.Proyecto.Final_BackEnd.model.Room;
import com.dh.Proyecto.Final_BackEnd.model.dto.RoomDto;
import com.dh.Proyecto.Final_BackEnd.repository.IRoomRepository;
import com.dh.Proyecto.Final_BackEnd.service.IImageService;
import com.dh.Proyecto.Final_BackEnd.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


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

    //ENCONTRAR TODAS LAS HABITACIONES
    @Override
    public List<RoomDto> findAllRooms() throws Exception {
        try {
            //Traer todas las entidades de la BD y guardarlas en una lista
            List<Room> rooms = roomRepository.findAll();

            //Si la room no tiene imágenes que devuelva una lista vacía en vez de null
            if (rooms.isEmpty()){
                return new ArrayList<>();
            }

            // Crear una lista vacía de roomDto para almacenarlos
            List<RoomDto> roomDtos = new ArrayList<>();
            for (Room room : rooms) {
                try {
                    // Crear un nuevo RoomDto con los datos básicos de la habitación
                    RoomDto roomDto = new RoomDto(
                            room.getRoomNumber(),
                            room.getCost(),
                            room.getAvailability()
                    );


                    // Recuperar las URLs de las imágenes para esta habitación
                    List<String> imageUrls = imageService.findImageUrls(room);

                    // Establecer las URLs de imágenes en el RoomDto
                    roomDto.setImagesUrl(imageUrls);

                    // Agregar el RoomDto a la lista
                    roomDtos.add(roomDto);

                }catch (Exception e){
                    throw new Exception("Unable to process room " + room.getRoomNumber() + e.getMessage(), e);
                }
            }
            return roomDtos;

        }catch (Exception e){
            throw new Exception("Unable to retrieve rooms: " + e.getMessage(), e);
        }





        //Crear una lista vacia de roomDto para almacenarlos
        /*List<RoomDto> roomDtos = new ArrayList<>();

        for (Room room: rooms) {
            roomDtos.add(new RoomDto(room.getRoomNumber(), room.getCost(), room.getAvailability());

            //Obtener las Urls de las imagenes para cada habitacion
            List<String> imageUrls = imageService.findImageUrls(room);

        }*/



    }


    @Override
    public void delete(Long id) {/*primero eliminar la imagen y despues la habitacion por que en la base de datos
    hay una relacion que es Determinada por la room. Primero en el image service se hace la eliminacion de la imagen
    y despues se asocia ese en el room service*/
        roomRepository.deleteById(id);

    }

    private String getBaseUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }
}
