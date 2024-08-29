package com.example.DATN.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Date;
import java.time.ZonedDateTime;
import java.util.Collection;

@Entity
@Table(name = "phim")
public class Phim {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "tenphim")
    private String tenPhim;
    @Column(name = "daodien" , length = 100)
    private String daoDien;
    @Column(name = "dienvien")
    private String dienVien;
    @Column(name = "noidung",columnDefinition = "text")
    private String noiDung;
    @Column(name = "thoiluong")
    private String thoiLuong;
    @Column(name = "ngonngu")
    private String ngonNghu;
    @Column(name = "video")
    private String video;
    @Column(name = "anh")
    private String anh;
    @Column(name = "ngayhieuluctu")
    private Date ngayHieuLucTu;
    @Column(name = "ngayhieulucden")
    private Date ngayHieuLucDen;
    @Column(name = "trangthai")
    private boolean trangThai;
    @Column(name = "theloai")
    private String theLoai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }

    public String getDaoDien() {
        return daoDien;
    }

    public void setDaoDien(String daoDien) {
        this.daoDien = daoDien;
    }

    public String getDienVien() {
        return dienVien;
    }

    public void setDienVien(String dienVien) {
        this.dienVien = dienVien;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getThoiLuong() {
        return thoiLuong;
    }

    public void setThoiLuong(String thoiLuong) {
        this.thoiLuong = thoiLuong;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }


    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getNgonNghu() {
        return ngonNghu;
    }

    public void setNgonNghu(String ngonNghu) {
        this.ngonNghu = ngonNghu;
    }

    public Date getNgayHieuLucTu() {
        return ngayHieuLucTu;
    }

    public void setNgayHieuLucTu(Date ngayHieuLucTu) {
        this.ngayHieuLucTu = ngayHieuLucTu;
    }

    public Phim(String tenPhim, String daoDien, String dienVien, String noiDung, String thoiLuong, String ngonNghu, String video, String anh, Date ngayHieuLucTu, Date ngayHieuLucDen, boolean trangThai, String theLoai) {
        this.tenPhim = tenPhim;
        this.daoDien = daoDien;
        this.dienVien = dienVien;
        this.noiDung = noiDung;
        this.thoiLuong = thoiLuong;
        this.ngonNghu = ngonNghu;
        this.video = video;
        this.anh = anh;
        this.ngayHieuLucTu = ngayHieuLucTu;
        this.ngayHieuLucDen = ngayHieuLucDen;
        this.trangThai = trangThai;
        this.theLoai = theLoai;
    }

    public Date getNgayHieuLucDen() {
        return ngayHieuLucDen;
    }

    public void setNgayHieuLucDen(Date ngayHieuLucDen) {
        this.ngayHieuLucDen = ngayHieuLucDen;
    }

    public Phim() {
    }
}
