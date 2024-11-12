package com.dh.Proyecto.Final_BackEnd.repository;

import com.dh.Proyecto.Final_BackEnd.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {
}