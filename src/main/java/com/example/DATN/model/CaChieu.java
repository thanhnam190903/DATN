package com.example.DATN.model;

import jakarta.persistence.*;

import java.sql.Time;

@Entity
@Table(name = "cachieu")
public class CaChieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "giochieu")
    private String gioChieu;
    @Column(name = "giokt")
    private Time giokt;
    @ManyToOne
    @JoinColumn(name = "id_ngaychieu")
    private NgayChieuS ngayChieu;
    @ManyToOne
    @JoinColumn(name = "id_phongchieu")
    private PhongChieu phongChieu;
    @ManyToOne
    @JoinColumn(name = "id_phim")
    private Phim phim;

    public Time getGiokt() {
        return giokt;
    }

    public void setGiokt(Time giokt) {
        this.giokt = giokt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getGioChieu() {
        return gioChieu;
    }

    public void setGioChieu(String gioChieu) {
        this.gioChieu = gioChieu;
    }

    public PhongChieu getPhongChieu() {
        return phongChieu;
    }

    public void setPhongChieu(PhongChieu phongChieu) {
        this.phongChieu = phongChieu;
    }

    public Phim getPhim() {
        return phim;
    }

    public void setPhim(Phim phim) {
        this.phim = phim;
    }

    public NgayChieuS getNgayChieu() {
        return ngayChieu;
    }

    public CaChieu(String gioChieu, Time giokt, NgayChieuS ngayChieu, PhongChieu phongChieu, Phim phim) {
        this.gioChieu = gioChieu;
        this.giokt = giokt;
        this.ngayChieu = ngayChieu;
        this.phongChieu = phongChieu;
        this.phim = phim;
    }

    public void setNgayChieu(NgayChieuS ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public CaChieu() {
    }
}
