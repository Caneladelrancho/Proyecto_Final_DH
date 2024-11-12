package com.dh.Proyecto.Final_BackEnd.Controller;

import com.dh.Proyecto.Final_BackEnd.model.dto.RoomDto;
import com.dh.Proyecto.Final_BackEnd.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rooms")
public class RoomController {

    //Inyectar dependencias de IRoomService
    private IRoomService iRoomService;

    @Autowired
    public RoomController(IRoomService iRoomService) {
        this.iRoomService = iRoomService;
    }

   //Guardar una habitación
    @PostMapping("/add")
    public ResponseEntity<Object> save(@ModelAttribute Object objeto){

        System.out.println(objeto.toString());

        try {
            //RoomDto savedRoom = iRoomService.saveRoom(roomDto);
             //return ResponseEntity.ok(savedRoom);
            return ResponseEntity.ok(objeto);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }







}
