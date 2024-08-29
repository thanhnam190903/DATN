package com.example.DATN.controller;

import com.example.DATN.Service.StorageService;
import com.example.DATN.model.Phim;
import com.example.DATN.repository.PhimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/quantri")
public class PhimController {

    private PhimRepository phimRepository;
    private StorageService storageService;

    @Autowired
    public PhimController(PhimRepository phimRepository,  StorageService storageService) {
        this.phimRepository = phimRepository;
        this.storageService = storageService;
    }
    @GetMapping("/phim")
    public String showPhim(Model model, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String key) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Phim> phims;
        if (!key.isEmpty()) {
            phims = phimRepository.searchPhim(key, pageable);
            model.addAttribute("key", key);
        } else {
            phims = phimRepository.findAllSorted(pageable);
        }
        model.addAttribute("phims", phims.getContent());
        model.addAttribute("totalPages", phims.getTotalPages());
        model.addAttribute("currentPage", page);

        return "admin/phim";
    }
    @GetMapping("add-phim")
    public String showAddPhim(Model model){
        Phim phim = new Phim();
        model.addAttribute("phim",phim);
        return "admin/addphim";
    }
    @PostMapping("/add-ph")
    public String insert(@ModelAttribute("phim") Phim phim , @RequestParam("fileVideo") MultipartFile fileVideo,@RequestParam("fileImage") MultipartFile fileImage ,Model model){
        this.storageService.store(fileVideo);
        this.storageService.store(fileImage);
        String findNameVideo = fileVideo.getOriginalFilename();
        String findNameImage = fileImage.getOriginalFilename();
        phim.setVideo(findNameVideo);
        phim.setAnh(findNameImage);
        Phim phim1 = phimRepository.findByTenPhim(phim.getTenPhim());
        if (phim1 == null){
            phimRepository.save(phim);
            return "redirect:/quantri/phim";
        }else{
            model.addAttribute("loi","Bạn đã nhập trùng tên với dữ liệu đã có sẵn! Hãy nhập lại");
            return "admin/addphim";
        }
    }
    @GetMapping("/update-phim")
    public String showUpdate(@RequestParam("id") Integer id ,Model model){
        Phim phim = phimRepository.findById(id).get();
        model.addAttribute("phim",phim);
        return "admin/updatephim";
    }
    @PostMapping("/update-ph")
    public String update(@ModelAttribute("phim") Phim phim , @RequestParam("fileVideo") MultipartFile fileVideo,@RequestParam("fileImage") MultipartFile fileImage ,Model model){
        Phim old_phim = phimRepository.findById(phim.getId()).orElse(null);
        if(fileVideo.isEmpty()) {
            phim.setVideo(old_phim.getVideo());

        }else {
            this.storageService.store(fileVideo);
            String findNameVideo = fileVideo.getOriginalFilename();
            phim.setVideo(findNameVideo);
        }
        if(fileImage.isEmpty()) {
            phim.setAnh(old_phim.getAnh());
        }else {
            this.storageService.store(fileImage);
            String findNameImage = fileImage.getOriginalFilename();
            phim.setAnh(findNameImage);
        }

        phimRepository.save(phim);
        return "redirect:/quantri/phim";
    }
    @GetMapping("/delete-phim")
    public String delete(@RequestParam("id") Integer id){
        phimRepository.deleteById(id);
        return "redirect:/quantri/phim";
    }

}
