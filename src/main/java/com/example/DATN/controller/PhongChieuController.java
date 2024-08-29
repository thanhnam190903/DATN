package com.example.DATN.controller;

import com.example.DATN.model.LoaiPhong;
import com.example.DATN.model.PhongChieu;
import com.example.DATN.model.Rap;
import com.example.DATN.repository.LoaiPhongRepository;
import com.example.DATN.repository.PhongChieuRepository;
import com.example.DATN.repository.RapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/quantri")
public class PhongChieuController {

    private PhongChieuRepository phongChieuRepository;
    private LoaiPhongRepository loaiPhongRepository;
    private RapRepository rapRepository;
    @Autowired
    public PhongChieuController(PhongChieuRepository phongChieuRepository, LoaiPhongRepository loaiPhongRepository, RapRepository rapRepository) {
        this.phongChieuRepository = phongChieuRepository;
        this.loaiPhongRepository = loaiPhongRepository;
        this.rapRepository = rapRepository;
    }

    @GetMapping("/phongchieu")
    public String showPhongChieu(Model model, @Param("key") String key, @RequestParam(value = "page", defaultValue = "1") int page) {
        int pageSize = 10;
        Page<PhongChieu> phongChieus;

        if (key != null && !key.isEmpty()) {
            phongChieus = phongChieuRepository.searchPhongChieu(key, PageRequest.of(page - 1, pageSize));
            model.addAttribute("key", key);
        } else {
            phongChieus = phongChieuRepository.findAllByOrderByIdDesc(PageRequest.of(page - 1, pageSize));
        }

        int totalPages = phongChieus.getTotalPages();
        int currentPage = phongChieus.getNumber() + 1;

        model.addAttribute("phongchieus", phongChieus);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPages", totalPages);

        return "admin/phongchieu";
    }
    @GetMapping("/add-phongchieu")
    public String createRap(Model model){
        PhongChieu phongChieu = new PhongChieu();
        model.addAttribute("phongchieu" , phongChieu);

        List<LoaiPhong> loaiPhongs = loaiPhongRepository.findAll();
        model.addAttribute("loaiphong",loaiPhongs);

        List<Rap> raps = rapRepository.findAll();
        model.addAttribute("rap",raps);
        return "admin/addphongchieu";
    }
    @PostMapping("/create-phongchieu")
    public String create(@ModelAttribute("phongchieu") PhongChieu phongChieu , Model model){
        PhongChieu phongChieu1 = phongChieuRepository.findByTenPhong(phongChieu.getTenPhong());
        if (phongChieu1 == null){
            phongChieuRepository.save(phongChieu);
            return "redirect:/quantri/phongchieu";
        }else{
            List<LoaiPhong> loaiPhongs = loaiPhongRepository.findAll();
            model.addAttribute("loaiphong",loaiPhongs);

            List<Rap> raps = rapRepository.findAll();
            model.addAttribute("rap",raps);
            model.addAttribute("loi","Bạn đã nhập trùng tên phòng chiếu đã có sẵn! Hãy nhập lại");
            return "admin/addphongchieu";
        }
    }
    @GetMapping("/update-phongchieu")
    public String showUpdateLoaiPhong(@RequestParam("id") Integer id, Model model){
        PhongChieu phongChieu = phongChieuRepository.findById(id).get();
        List<LoaiPhong> loaiPhongs = loaiPhongRepository.findAll();
        model.addAttribute("loaiphong",loaiPhongs);

        List<Rap> raps = rapRepository.findAll();
        model.addAttribute("rap",raps);

        model.addAttribute("phongchieu",phongChieu);
        return "admin/updatephongchieu";
    }
    @PostMapping("/update-pc")
    public String update(@ModelAttribute("phongchieu") PhongChieu phongChieu ){
            phongChieuRepository.saveAndFlush(phongChieu);
            return "redirect:/quantri/phongchieu";
    }
    @GetMapping("/delete-phongchieu")
    public String delete(@RequestParam("id") Integer id){
        phongChieuRepository.deleteById(id);
        return "redirect:/quantri/phongchieu";
    }
}
