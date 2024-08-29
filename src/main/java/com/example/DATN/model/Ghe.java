package com.example.DATN.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ghe")
public class Ghe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  int id;
    @Column(name = "hang",length = 1)
    private String hang;
    @Column(name = "cot")
    private int cot;
    @Column(name = "soghe")
    private String soGhe;
    @Column(name = "trangthai")
    private boolean trangThai;
    @ManyToOne
    @JoinColumn(name = "id_loaighe")
    private LoaiGhe loaiGhe;
    @ManyToOne
    @JoinColumn(name = "id_phongchieu")
    private PhongChieu phongChieu;
    @ManyToOne
    @JoinColumn(name = "datve_id")
    private DatVe datVe;

    public DatVe getDatVe() {
        return datVe;
    }

    public void setDatVe(DatVe datVe) {
        this.datVe = datVe;
    }

    public String getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(String soGhe) {
        this.soGhe = soGhe;
    }

    public Ghe(String hang, int cot, String soGhe, boolean trangThai, LoaiGhe loaiGhe, PhongChieu phongChieu) {
        this.hang = hang;
        this.cot = cot;
        this.soGhe = soGhe;
        this.trangThai = trangThai;
        this.loaiGhe = loaiGhe;
        this.phongChieu = phongChieu;
    }

    public Ghe(String hang, int cot, String soGhe, boolean trangThai, LoaiGhe loaiGhe, PhongChieu phongChieu, DatVe datVe) {
        this.hang = hang;
        this.cot = cot;
        this.soGhe = soGhe;
        this.trangThai = trangThai;
        this.loaiGhe = loaiGhe;
        this.phongChieu = phongChieu;
        this.datVe = datVe;
    }

    public String getHang() {
        return hang;
    }

    public void setHang(String hang) {
        this.hang = hang;
    }

    public int getCot() {
        return cot;
    }

    public void setCot(int cot) {
        this.cot = cot;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public LoaiGhe getLoaiGhe() {
        return loaiGhe;
    }

    public void setLoaiGhe(LoaiGhe loaiGhe) {
        this.loaiGhe = loaiGhe;
    }

    public PhongChieu getPhongChieu() {
        return phongChieu;
    }

    public void setPhongChieu(PhongChieu phongChieu) {
        this.phongChieu = phongChieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public Ghe() {
    }
}
