package com.example.DATN.repository;

import com.example.DATN.model.Phim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;

public interface PhimRepository extends JpaRepository<Phim,Integer> {
    public Phim findByTenPhim(String tenPhim);
    Page<Phim> findByNgayHieuLucTuLessThanEqualAndNgayHieuLucDenGreaterThanEqualAndTrangThaiIsTrue(Date ngayHieuLucTu, Date ngayHieuLucDen, Pageable pageable);

    List<Phim> findByNgayHieuLucTuLessThanEqualAndNgayHieuLucDenGreaterThanEqualAndTrangThaiIsTrue(Date ngayHieuLucTu, Date ngayHieuLucDen);

    @Query("SELECT p FROM Phim p WHERE p.ngayHieuLucTu > :currentDate AND p.trangThai = true")
    List<Phim> findAllComingSoonMovies(@Param("currentDate") Date currentDate);
    @Query("SELECT p FROM Phim p WHERE p.tenPhim LIKE %?1% " +
            "OR p.daoDien LIKE %?1% " +
            "OR p.dienVien LIKE %?1% " +
            "OR p.theLoai LIKE %?1% " +
            "OR p.ngonNghu LIKE %?1% " +
            "OR p.thoiLuong LIKE %?1%")
    Page<Phim> searchPhim(String key,Pageable pageable);

    @Query("SELECT p FROM Phim p ORDER BY p.id DESC")
    Page<Phim> findAllSorted(Pageable pageable);
}
