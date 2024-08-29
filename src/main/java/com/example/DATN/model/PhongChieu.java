package com.example.DATN.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "phongchieu")
public class PhongChieu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "tenphong" ,length = 100)
    private String tenPhong;
    @Column(name = "trangthai")
    private boolean trangThai;
    @ManyToOne
    @JoinColumn(name = "id_rap")
    private Rap rap;
    @ManyToOne
    @JoinColumn(name = "id_loaiphong")
    private LoaiPhong loaiPhong;
    @OneToMany(mappedBy = "phongChieu")
    private List<Ghe> gheList;
    @OneToMany(mappedBy = "phongChieu")
    private List<CaChieu> caChieus;

    public List<CaChieu> getCaChieus() {
        return caChieus;
    }

    public void setCaChieus(List<CaChieu> caChieus) {
        this.caChieus = caChieus;
    }

    public List<Ghe> getGheList() {
        return gheList;
    }

    public void setGheList(List<Ghe> gheList) {
        this.gheList = gheList;
    }

    public PhongChieu(String tenPhong, boolean trangThai, Rap rap, LoaiPhong loaiPhong, List<Ghe> gheList) {
        this.tenPhong = tenPhong;
        this.trangThai = trangThai;
        this.rap = rap;
        this.loaiPhong = loaiPhong;
        this.gheList = gheList;
    }

    public PhongChieu(String tenPhong, boolean trangThai, Rap rap, LoaiPhong loaiPhong, List<Ghe> gheList, List<CaChieu> caChieus) {
        this.tenPhong = tenPhong;
        this.trangThai = trangThai;
        this.rap = rap;
        this.loaiPhong = loaiPhong;
        this.gheList = gheList;
        this.caChieus = caChieus;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenPhong() {
        return tenPhong;
    }

    public void setTenPhong(String tenPhong) {
        this.tenPhong = tenPhong;
    }

    public boolean getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Rap getRap() {
        return rap;
    }

    public void setRap(Rap rap) {
        this.rap = rap;
    }

    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    public void setLoaiPhong(LoaiPhong loaiPhong) {
        this.loaiPhong = loaiPhong;
    }

    public PhongChieu(String tenPhong, boolean trangThai, Rap rap, LoaiPhong loaiPhong) {
        this.tenPhong = tenPhong;
        this.trangThai = trangThai;
        this.rap = rap;
        this.loaiPhong = loaiPhong;
    }

    public PhongChieu() {
    }
}
