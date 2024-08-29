package com.example.DATN.repository;

import com.example.DATN.model.CaChieu;
import com.example.DATN.model.Phim;
import com.example.DATN.model.PhongChieu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

public interface CaChieuRepository extends JpaRepository<CaChieu,Integer> {

    @Query("SELECT c FROM CaChieu c " +
            "JOIN c.ngayChieu n " +
            "JOIN c.phongChieu p " +
            "JOIN c.phim f " +
            "WHERE c.gioChieu LIKE %?1% " +
            "OR FUNCTION('FORMAT', n.ngayChieu, 'yyyy-MM-dd') LIKE %?1% " +
            "OR p.tenPhong LIKE %?1% " +
            "OR f.tenPhim LIKE %?1%")
    Page<CaChieu> searchCaChieu(String keyword,Pageable pageable);

    @Query("SELECT DISTINCT c.ngayChieu.ngayChieu FROM CaChieu c WHERE c.phim.id = :idPhim AND c.ngayChieu.ngayChieu >= :today ORDER BY c.ngayChieu.ngayChieu ASC")
    List<Date> findAllNgayChieuByIdPhim(@Param("idPhim") int idPhim, @Param("today") Date today);

    @Query("SELECT DISTINCT c.phongChieu.loaiPhong.tenLoaiPhong FROM CaChieu c")
    List<String> findAllLoaiPhongNames();

    @Query("SELECT DISTINCT lp.tenLoaiPhong FROM LoaiPhong lp " +
            "JOIN PhongChieu pc ON lp.id = pc.loaiPhong.id " +
            "JOIN CaChieu cc ON pc.id = cc.phongChieu.id " +
            "WHERE cc.phim.id = :phimId")
    List<String> findLoaiPhongNamesByPhimId(@Param("phimId") int phimId);

    @Query("SELECT c.gioChieu FROM CaChieu c " +
            "JOIN c.phongChieu pc " +
            "JOIN pc.rap r " +
            "WHERE r.tenRap = :tenRap")
    List<String> findGioChieuByRap(String tenRap);

    @Query("SELECT c FROM CaChieu c WHERE c.phim.id = :idPhim AND c.ngayChieu.ngayChieu = :ngayChieu AND c.phongChieu.rap.diaChi = :diaChi AND c.phongChieu.loaiPhong.tenLoaiPhong = :loaiPhong")
    List<CaChieu> findGioChieuByPhimNgayChieuDiaChiLoaiPhong(@Param("idPhim") int idPhim,
                                                             @Param("ngayChieu") Date ngayChieu,
                                                             @Param("diaChi") String diaChi,
                                                             @Param("loaiPhong") String loaiPhong);

    @Query("SELECT c FROM CaChieu c " +
            "JOIN c.ngayChieu nc " +
            "JOIN c.phongChieu pc " +
            "JOIN pc.loaiPhong lp " +
            "JOIN pc.rap r " +
            "WHERE c.phim.id = :phimId " +
            "AND nc.ngayChieu = :ngayChieu " +
            "AND r.diaChi = :diaChi " +
            "AND lp.tenLoaiPhong = :loaiPhong " +
            "AND c.gioChieu = :gioChieu")
    List<CaChieu> findCaChieuByPhimNgayChieuDiaChiLoaiPhong(@Param("phimId") int phimId,
                                                            @Param("ngayChieu") Date ngayChieu,
                                                            @Param("diaChi") String diaChi,
                                                            @Param("loaiPhong") String loaiPhong,
                                                            @Param("gioChieu") String gioChieu);

    @Query("SELECT c FROM CaChieu c WHERE c.phongChieu = :phim AND c.giokt < :now")
    List<CaChieu> findByPhongCHieuAndGioktBefore(@Param("phim") PhongChieu phongChieu, @Param("now") Time now);

    @Query("SELECT cc FROM CaChieu cc ORDER BY cc.id DESC")
    Page<CaChieu> findAllOrderByIdDesc(Pageable pageable);
}
