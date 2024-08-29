package com.example.DATN.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.text.SimpleDateFormat;

@Entity
@Table(name = "ngaychieu")
public class NgayChieuS {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "ngaychieu")
    private Date ngayChieu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getNgayChieu() {
        return ngayChieu;
    }

    public void setNgayChieu(Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public NgayChieuS(Date ngayChieu) {
        this.ngayChieu = ngayChieu;
    }

    public NgayChieuS() {
    }
    public String getNgayChieuAsString() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(this.ngayChieu);
    }
}
