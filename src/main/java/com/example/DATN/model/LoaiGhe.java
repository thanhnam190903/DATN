package com.example.DATN.model;

import jakarta.persistence.*;

@Entity
@Table(name = "loaighe")
public class LoaiGhe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "tenloai" , length = 100)
    private String tenLoai;

    @Column(name = "giaghe")
    private int giaGhe;

    public int getGiaGhe() {
        return giaGhe;
    }

    public void setGiaGhe(int giaGhe) {
        this.giaGhe = giaGhe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public LoaiGhe(String tenLoai , int giaGhe) {
        this.tenLoai = tenLoai;
        this.giaGhe = giaGhe;
    }

    public LoaiGhe() {
    }
}
