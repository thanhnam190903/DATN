package com.example.DATN.controller;

import com.example.DATN.model.*;
import com.example.DATN.repository.DatVeRepository;
import com.example.DATN.repository.GheRepository;
import com.example.DATN.repository.HoaDonRepository;
import com.example.DATN.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@Controller
public class PaymentController {
    @Autowired
    private DatVeRepository datVeRepository;
    @Autowired
    private HoaDonRepository hoaDonRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GheRepository gheRepository;

    @GetMapping("/thanhtoan")
    public String hinhthucthanhtoan(@RequestParam("id_datve") int idDatVe, Model model){
        DatVe datVe = datVeRepository.findById(idDatVe).get();
        model.addAttribute("dave",datVe);
        HoaDon hoaDon = new HoaDon();
        model.addAttribute("hoadon",hoaDon);
        model.addAttribute("idDatVe", datVe.getId());
        model.addAttribute("danhSachGhe", datVe.getDanhSachGhe());
        double tongGia = datVe.tinhTongGia();  // Tính tổng giá
        model.addAttribute("tongGia", tongGia);

        return "client/payment";
    }

    @PostMapping("/chuyenkhoan")
    public ResponseEntity<?> thanhToan(@RequestParam("amount") double amount,
                                       @RequestParam("id_datve") int idDatVe,
                                       @RequestParam("paymentMethod") String paymentMethod,
                                       Principal principal) {
        DatVe datVe = datVeRepository.findById(idDatVe).get();
        String username = principal.getName();
        User user = userRepository.findByUsername(username);

        HoaDon hoaDon = new HoaDon();
        hoaDon.setNgayLap(java.sql.Date.valueOf(LocalDate.now()));
        hoaDon.setGioLap(Time.valueOf(LocalTime.now()));
        hoaDon.setTongTien(amount);
        hoaDon.setTrangThai(false);
        hoaDon.setUser(user);
        hoaDon.setDatVe(datVe);
        hoaDon.setHttt(paymentMethod.equals("bankTransfer") ? "Chuyển khoản" : "Thanh toán tại quầy");

        HoaDon savedHoaDon = hoaDonRepository.save(hoaDon);
        return ResponseEntity.ok(savedHoaDon.getId());
    }

    @GetMapping("/xoa-dat-ve")
    public String xoaDatVe(@RequestParam("id_datve") int idDatVe, RedirectAttributes redirectAttributes) {
        try {
            // Lấy thông tin đặt vé
            DatVe datVe = datVeRepository.findById(idDatVe)
                    .orElseThrow(() -> new RuntimeException("Đặt vé không tồn tại"));

            // Cập nhật trạng thái của các ghế
            for (Ghe ghe : datVe.getDanhSachGhe()) {
                ghe.setTrangThai(false);  // Đặt lại trạng thái ghế
                ghe.setDatVe(null);  // Xóa liên kết với DatVe
                gheRepository.saveAndFlush(ghe);  // Lưu lại trạng thái ghế
            }

            // Xóa đặt vé
            datVeRepository.delete(datVe);

            redirectAttributes.addFlashAttribute("message", "Đặt vé đã được xóa thành công.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Có lỗi xảy ra khi xóa đặt vé.");
        }
        return "redirect:/home";
    }

}

