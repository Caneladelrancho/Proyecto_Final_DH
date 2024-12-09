package com.dh.Proyecto.Final_BackEnd.Controller;

import com.dh.Proyecto.Final_BackEnd.model.dto.RoomDto;
import com.dh.Proyecto.Final_BackEnd.service.IRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<RoomDto> save(@ModelAttribute RoomDto roomDto){
        try {
            return ResponseEntity.ok(iRoomService.saveRoom(roomDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping
    public ResponseEntity<?> findAllRooms() throws Exception {
        try {
            List<RoomDto> rooms = iRoomService.findAllRooms();
            return ResponseEntity.ok(rooms);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}



    /*@PostMapping("/add")
    public ResponseEntity<RoomDto> save(
            @ModelAttribute RoomDto roomDto,
            @RequestPart("images") List<MultipartFile> images){

        roomDto.getImages().forEach(imageDto -> imageDto.setImages(images));

        try {
            RoomDto savedRoom = iRoomService.saveRoom(roomDto);
            return ResponseEntity.ok(savedRoom);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }*/



    /*public ResponseEntity<RoomDto> save(
            @ModelAttribute RoomDto roomDto),
            @RequestPart("images") List<MultipartFile> images){

        roomDto.get

        try {
            RoomDto savedRoom = iRoomService.saveRoom(roomDto);
            return ResponseEntity.ok(savedRoom);

        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }*/

    /*@PostMapping("/add")
    public ResponseEntity<RoomDto> save(@ModelAttribute RoomDto roomDto){

        try {
            return ResponseEntity.ok(iRoomService.saveRoom(roomDto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }*/








