package com.example.DATN.Service;

import com.example.DATN.model.CaChieu;
import com.example.DATN.model.Ghe;
import com.example.DATN.model.Phim;
import com.example.DATN.model.PhongChieu;
import com.example.DATN.repository.CaChieuRepository;
import com.example.DATN.repository.GheRepository;
import com.example.DATN.repository.PhimRepository;
import com.example.DATN.repository.PhongChieuRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class ScheduledTasks {
    @Autowired
    private CaChieuRepository caChieuRepository;

    @Autowired
    private GheRepository gheRepository;

    @Autowired
    private PhongChieuRepository phongChieuRepository;

    // Sẽ chạy mỗi giờ
//    @Scheduled(cron = "0 0 * * * ?")
//    @Transactional
//    public void updateGheStatus() {
//        System.out.println("Phương thức updateGheStatus được gọi vào: " + LocalTime.now());
//
//        LocalTime nowLocalTime = LocalTime.now();
//        Time nowTime = Time.valueOf(nowLocalTime);
//
//        // Lấy danh sách phòng chiếu
//        List<PhongChieu> phongChieus = phongChieuRepository.findAll();
//        System.out.println("Danh sách phòng chiếu: " + phongChieus);
//
//        for (PhongChieu phongChieu : phongChieus) {
//            // Lấy danh sách ca chiếu của phòng chiếu đó mà giờ kết thúc đã qua thời gian hiện tại
//            List<CaChieu> caChieus = caChieuRepository.findByPhongCHieuAndGioktBefore(phongChieu, nowTime);
//            System.out.println("Ca chiếu cho phòng chiếu " + phongChieu.getTenPhong() + ": " + caChieus);
//
//            if (!caChieus.isEmpty()) {
//                // Cập nhật trạng thái ghế
//                List<Ghe> gheList = gheRepository.findByPhongChieu(phongChieu);
//                System.out.println("Danh sách ghế: " + gheList);
//
//                for (Ghe ghe : gheList) {
//                    ghe.setTrangThai(false);
//                    System.out.println("Cập nhật trạng thái ghế: " + ghe.getId() + " thành false");
//                }
//                gheRepository.saveAll(gheList);
//            }
//        }
//    }
}
