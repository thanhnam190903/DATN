package com.example.DATN.repository;

import com.example.DATN.model.LoaiPhong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LoaiPhongRepository extends JpaRepository<LoaiPhong , Integer> {
    public LoaiPhong findByTenLoaiPhong(String tenLoaiPhong);
    @Query("SELECT lp FROM LoaiPhong lp WHERE lp.tenLoaiPhong LIKE %?1%")
    List<LoaiPhong> searchLoaiPhong(String key);


}
