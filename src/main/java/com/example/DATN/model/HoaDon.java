package com.example.DATN.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "hoadon")
public class HoaDon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "ngaylap")
    private Date ngayLap;
    @Column(name = "giolap")
    private Time gioLap;
    @Column(name = "tongtien")
    private double tongTien;
    @Column(name = "trangthai")
    private Boolean trangThai;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name = "id_datve")
    private DatVe datVe;
    @Column(name = "httt")
    private String httt;

    public String getHttt() {
        return httt;
    }

    public void setHttt(String httt) {
        this.httt = httt;
    }

    public int getId() {
        return id;
    }

    public Time getGioLap() {
        return gioLap;
    }

    public HoaDon(Date ngayLap, Time gioLap, double tongTien, Boolean trangThai, User user, DatVe datVe,String httt) {
        this.ngayLap = ngayLap;
        this.gioLap = gioLap;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
        this.user = user;
        this.datVe = datVe;
        this.httt = httt;
    }

    public void setGioLap(Time gioLap) {
        this.gioLap = gioLap;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public Boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Boolean trangThai) {
        this.trangThai = trangThai;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DatVe getDatVe() {
        return datVe;
    }

    public void setDatVe(DatVe datVe) {
        this.datVe = datVe;
    }

    public HoaDon() {
    }
}
