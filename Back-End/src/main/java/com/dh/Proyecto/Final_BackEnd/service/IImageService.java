package com.dh.Proyecto.Final_BackEnd.service;

import com.dh.Proyecto.Final_BackEnd.model.Image;
import com.dh.Proyecto.Final_BackEnd.model.Room;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    Image saveImage(MultipartFile file, Room room) throws IOException; //Guardar Imagen
    List<String> findImageUrls(Room room) throws Exception;
    void delete (Long id);
}
