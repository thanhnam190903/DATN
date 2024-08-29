package com.example.DATN.repository;

import com.example.DATN.model.DatVe;
import com.example.DATN.model.HoaDon;
import com.example.DATN.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HoaDonRepository extends JpaRepository<HoaDon,Integer> {
    @Query("SELECT h.user FROM HoaDon h WHERE h.id = :hoaDonId")
    User findUserByHoaDonId(@Param("hoaDonId") int hoaDonId);
    @Query("SELECT h.datVe FROM HoaDon h WHERE h.id = :hoaDonId ")
    DatVe findDatVeByHoaDonId(@Param("hoaDonId") int hoaDonId);
    @Query("SELECT hd FROM HoaDon hd WHERE hd.id = ?1")
    List<HoaDon> searchHoaDonByKey(int key);

    List<HoaDon> findByUser_Username(String username);

    @Query("SELECT r.tenRap, YEAR(h.ngayLap), MONTH(h.ngayLap), SUM(h.tongTien) " +
            "FROM HoaDon h " +
            "JOIN h.datVe dv " +
            "JOIN dv.rap r " +
            "WHERE r.tenRap = :tenrap " +
            "GROUP BY r.tenRap, YEAR(h.ngayLap), MONTH(h.ngayLap) " +
            "ORDER BY r.tenRap, YEAR(h.ngayLap), MONTH(h.ngayLap)")
    List<Object[]> getMonthlyRevenueByRap(String tenrap);

//    @Query("SELECT r.tenRap, p.tenPhim, " +
//            "SUM(CASE WHEN g.trangThai = true THEN 1 ELSE 0 END) as soldTickets, " +
//            "SUM(CASE WHEN g.trangThai = false THEN 1 ELSE 0 END) as availableTickets " +
//            "FROM HoaDon h " +
//            "JOIN h.datVe dv " +
//            "JOIN dv.danhSachGhe g " +
//            "JOIN dv.caChieu cc " +
//            "JOIN cc.phim p " +
//            "JOIN dv.rap r " +
//            "WHERE r.tenRap = :tenrap " +
//            "GROUP BY r.tenRap, p.tenPhim " +
//            "ORDER BY r.tenRap, p.tenPhim")
@Query("SELECT r.tenRap, p.tenPhim, " +
        "SUM(CASE WHEN g.trangThai = true THEN 1 ELSE 0 END) as soldTickets, " +
        "SUM(CASE WHEN g.trangThai = false THEN 1 ELSE 0 END) as availableTickets " +
        "FROM Ghe g " +
        "JOIN g.phongChieu pc " +
        "JOIN pc.rap r " +
        "JOIN pc.caChieus cc " +
        "JOIN cc.phim p " +
        "WHERE r.tenRap = :tenrap " +
        "GROUP BY r.tenRap, p.tenPhim " +
        "ORDER BY r.tenRap, p.tenPhim")
    List<Object[]> getTicketStatisticsByRap(String tenrap);

}
