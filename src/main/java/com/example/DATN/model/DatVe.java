package com.example.DATN.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "datve")
public class DatVe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "soluong")
    private int soluong;
    @ManyToOne
    @JoinColumn(name = "id_cachieu")
    private CaChieu caChieu;
    @ManyToOne
    @JoinColumn(name = "id_rap")
    private Rap rap;
    @OneToMany(mappedBy = "datVe", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, orphanRemoval = false)
    private List<Ghe> danhSachGhe;

    public DatVe(int soluong, CaChieu caChieu, Rap rap, List<Ghe> danhSachGhe) {
        this.soluong = soluong;
        this.caChieu = caChieu;
        this.rap = rap;
        this.danhSachGhe = danhSachGhe;
    }

    public List<Ghe> getDanhSachGhe() {
        return danhSachGhe;
    }

    public void setDanhSachGhe(List<Ghe> danhSachGhe) {
        this.danhSachGhe = danhSachGhe;
    }

    public DatVe() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public CaChieu getCaChieu() {
        return caChieu;
    }

    public void setCaChieu(CaChieu caChieu) {
        this.caChieu = caChieu;
    }

    public Rap getRap() {
        return rap;
    }

    public void setRap(Rap rap) {
        this.rap = rap;
    }
    public double tinhTongGia() {
        return danhSachGhe.stream()
                .mapToDouble(ghe -> ghe.getLoaiGhe().getGiaGhe())
                .sum();
    }
}
