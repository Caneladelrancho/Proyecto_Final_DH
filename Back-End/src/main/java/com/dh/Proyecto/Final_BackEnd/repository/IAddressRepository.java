package com.dh.Proyecto.Final_BackEnd.repository;

import com.dh.Proyecto.Final_BackEnd.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address, Long> {
}
