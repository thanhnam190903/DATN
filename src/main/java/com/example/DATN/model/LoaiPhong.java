package com.example.DATN.model;

import jakarta.persistence.*;

@Entity
@Table(name = "loaiphong")
public class LoaiPhong {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "tenloaiphong" , length = 100)
    private String tenLoaiPhong;
    @Column(name = "trangthai")
    private boolean trangThai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        this.tenLoaiPhong = tenLoaiPhong;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public LoaiPhong( String tenLoaiPhong, boolean trangThai) {
        this.tenLoaiPhong = tenLoaiPhong;
        this.trangThai = trangThai;
    }

    public LoaiPhong() {
    }
}
