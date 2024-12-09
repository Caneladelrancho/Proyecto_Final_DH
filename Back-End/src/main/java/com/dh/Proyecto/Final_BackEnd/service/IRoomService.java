package com.dh.Proyecto.Final_BackEnd.service;

import com.dh.Proyecto.Final_BackEnd.model.Room;
import com.dh.Proyecto.Final_BackEnd.model.dto.RoomDto;

import java.io.IOException;
import java.util.List;

public interface IRoomService {

    RoomDto saveRoom(RoomDto roomDto) throws IOException; //Agregar producto o habitacion
    List<RoomDto> findAllRooms() throws Exception;//Listar todos los productos que ya esten registrados
    void delete(Long id);//Eliminar los productos
}
