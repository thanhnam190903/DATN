package com.example.DATN.model;

import jakarta.persistence.*;

@Entity
@Table(name = "raps")
public class Rap {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "tenrap" , length = 150)
    private String tenRap;
    @Column(name = "diachi" , length = 150)
    private String diaChi;
    @Column(name = "trangthai")
    private boolean trangThai;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenRap() {
        return tenRap;
    }

    public void setTenRap(String tenRap) {
        this.tenRap = tenRap;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Rap(String tenRap, String diaChi, boolean trangThai) {
        this.tenRap = tenRap;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
    }

    public Rap() {
    }
}
