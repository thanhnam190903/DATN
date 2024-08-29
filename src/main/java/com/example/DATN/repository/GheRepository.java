package com.example.DATN.repository;

import com.example.DATN.model.Ghe;
import com.example.DATN.model.PhongChieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GheRepository extends JpaRepository<Ghe , Integer> {
    List<Ghe> findByPhongChieuIdAndHang(Integer phongChieuId ,String hang);
    List<Ghe> findByPhongChieuIdAndTrangThai(Integer phongChieuId, boolean trangThai);

    @Query("SELECT g FROM Ghe g " +
            "JOIN g.loaiGhe lg " +
            "JOIN g.phongChieu pc " +
            "WHERE g.soGhe LIKE %?1% " +
            "OR g.hang LIKE %?1% " +
            "OR pc.tenPhong LIKE %?1% " +
            "OR lg.tenLoai LIKE %?1% ")
    Page<Ghe> searchGheByString(String key,Pageable pageable);

    @Query("SELECT g FROM Ghe g " +
            "JOIN g.loaiGhe lg " +
            "JOIN g.phongChieu pc " +
            "WHERE g.cot = ?1 " +
            "or lg.giaGhe = ?1")
    Page<Ghe> searchGheByInt(int cot,Pageable pageable);
    @Query("SELECT g FROM Ghe g ORDER BY g.id DESC")
    Page<Ghe> findAllGhe(Pageable pageable);

    List<Ghe> findByPhongChieu(PhongChieu phongChieu);
}
