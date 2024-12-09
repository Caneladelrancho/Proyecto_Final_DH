package com.dh.Proyecto.Final_BackEnd.service.impl;

import com.dh.Proyecto.Final_BackEnd.model.Image;
import com.dh.Proyecto.Final_BackEnd.model.Room;
import com.dh.Proyecto.Final_BackEnd.repository.IImageRepository;
import com.dh.Proyecto.Final_BackEnd.service.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImageService implements IImageService {

    @Autowired
    private IImageRepository imageRepository;

    //Constante para almacenar la ruta de la carpeta de las imágenes
    private static final Path ROOT_LOCATION = Paths.get("../Img_proyecto_final");

    //Tamaño máximo de archivo permitido, 10mb
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024;

    //Formatos permitidos para las imágenes
    private static final List<String> ALLOWED_FORMATS = Arrays.asList("image/jpeg", "image/jpg", "image/png");

    //Validar los archivos de las imágenes
    private void validateImageFile(MultipartFile file) throws IOException{

        if (file.isEmpty()){
            throw new IOException("The file is empty");
        }

        if (file.getSize() > MAX_FILE_SIZE){
            throw new IOException("File size exceeds maximum allowed of 10MB");
        }

        if (!ALLOWED_FORMATS.contains(file.getContentType())){
            throw new IOException("Invalid format file. Only JPG, JPEG and PNG are allowed");
        }

    }
    //Limpiar el nombre de la imagen en caso tal de que tenga caracteres no válidos
    private String sanitizeFileName(String filename) {
        return filename.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
    }

    private String processFileName(String originalFileName) throws IOException{
        if (originalFileName == null){
            throw new IOException("Invalid file name");
        }

        String sanitizedFileName = sanitizeFileName(originalFileName);

        return UUID.randomUUID().toString() + "_" + sanitizedFileName;
    }

    @Override
    public Image saveImage(MultipartFile file, Room room) throws IOException {

        //Ejecutar validaciones de la imagen
        validateImageFile(file);

        // Crear carpeta si no existe
        if (!Files.exists(ROOT_LOCATION)) {
            Files.createDirectories(ROOT_LOCATION);
        }
        //Procesar nombre del archivo y obtener la ruta de destino
        String fileName = processFileName(file.getOriginalFilename());
        Path destinationFile = ROOT_LOCATION.resolve(Paths.get(fileName));

        //Guardar archivo en el sistema
        try {
            Files.copy(file.getInputStream(), destinationFile);
        }catch (IOException e){
            throw new IOException("Failed to store file " + destinationFile.getFileName(), e);
        }

        //Crear y guardar la entidad Image
        Image image = new Image(file.getName(), "Description img", destinationFile.toString(), room);
        System.out.println(image.toString());
        imageRepository.save(image);//Guardar imagen en la BD

        return image;
    }

    @Override
    public List<String> findImageUrls(Room room) throws Exception {
        try {
            //Recuperar todas las imágenes de una habitación específica
            List<Image> images = imageRepository.findByRoom(room);

            //Convertir las imagenes a URLs
            return images.stream()
                    .map(Image::getImageUrl)
                    .collect(Collectors.toList());

        }catch (Exception e){
            throw new Exception("Unable to retrieve images" + e.getMessage(), e);
        }
    }

    @Override
    public void delete(Long id) {

    }




}
