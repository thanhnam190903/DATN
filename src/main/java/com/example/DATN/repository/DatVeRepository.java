package com.example.DATN.repository;

import com.example.DATN.model.DatVe;
import com.example.DATN.model.Rap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DatVeRepository extends JpaRepository<DatVe,Integer> {
    @Query("SELECT v.rap From DatVe v WHERE v.id = :datVeId")
    Rap findRapByDatVeId(@Param("datVeId") int id_datve);
}
