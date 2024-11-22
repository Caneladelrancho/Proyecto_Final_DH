package com.dh.Proyecto.Final_BackEnd.service;

import com.dh.Proyecto.Final_BackEnd.model.Image;
import com.dh.Proyecto.Final_BackEnd.model.Room;
import com.dh.Proyecto.Final_BackEnd.model.dto.ImageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IImageService {
    Image saveImage(MultipartFile file, Room room) throws IOException; //Guardar Imagen
    List<Image> findAllImaged();
    void delete (Long id);
}
