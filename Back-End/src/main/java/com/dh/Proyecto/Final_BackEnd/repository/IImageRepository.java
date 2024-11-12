package com.dh.Proyecto.Final_BackEnd.repository;

import com.dh.Proyecto.Final_BackEnd.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageRepository extends JpaRepository<Image, Long> {
}
