package com.example.DATN.repository;

import com.example.DATN.model.LoaiGhe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoaiGheRepository extends JpaRepository<LoaiGhe ,Integer> {
    public LoaiGhe findByTenLoai(String tenLoai);
    @Query("SELECT lg FROM LoaiGhe lg WHERE lg.tenLoai LIKE %?1%")
    List<LoaiGhe> searchLoaiGhe(String key);
    @Query("SELECT lg FROM LoaiGhe lg WHERE lg.giaGhe = ?1")
    List<LoaiGhe> searchLoaiGheByInt(int key);
}
