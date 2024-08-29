package com.example.DATN.repository;

import com.example.DATN.model.LoaiPhong;
import com.example.DATN.model.PhongChieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PhongChieuRepository extends JpaRepository<PhongChieu , Integer> {
    public PhongChieu findByTenPhong(String tenPhong);
    @Query("SELECT pc FROM PhongChieu pc " +
            "JOIN pc.rap r " +
            "JOIN pc.loaiPhong lp " +
            "WHERE pc.tenPhong LIKE %?1% " +
            "OR lp.tenLoaiPhong LIKE %?1% " +
            "OR r.tenRap LIKE %?1% " +
            "OR r.diaChi LIKE %?1%")
    Page<PhongChieu> searchPhongChieu(String key,Pageable pageable);

    Page<PhongChieu> findAllByOrderByIdDesc(Pageable pageable);
}
