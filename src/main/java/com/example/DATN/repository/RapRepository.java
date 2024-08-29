package com.example.DATN.repository;

import com.example.DATN.model.Rap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "rap")
public interface RapRepository extends JpaRepository<Rap ,Integer> {
    public Rap findByTenRap(String tenRap);
    @Query("SELECT DISTINCT r.diaChi FROM Rap r")
    List<String> findDistinctDiaChi();

    List<Rap> findByDiaChi(String diaChi);

    @Query("SELECT r FROM Rap r WHERE r.tenRap LIKE %?1% or r.diaChi LIKE %?1%")
    List<Rap> searchRap(String key);
}
