package com.example.DATN.repository;

import com.example.DATN.model.NgayChieuS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface NgayChieuRepository extends JpaRepository<NgayChieuS,Integer> {
    public NgayChieuS findByNgayChieu(Date ngayChieu);
    @Query("SELECT nc FROM NgayChieuS nc WHERE nc.id = ?1")
    List<NgayChieuS> searchNgayChieu(@Param("key") int key);
}
