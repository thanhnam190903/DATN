package com.example.DATN.controller;

import com.example.DATN.model.Rap;
import com.example.DATN.repository.RapRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = {"/quantri", "/home"})
public class RapController {
    @Autowired
    private RapRepository rapRepository;

    @GetMapping("/rap")
    public String showRap(Model model , @Param("key") String key){
        List<Rap> raps = rapRepository.findAll();
        if (key != null){
            raps = rapRepository.searchRap(key);
            model.addAttribute("key",key);
        }
        model.addAttribute("raps",raps);
        return "admin/rap";
    }
    @GetMapping("/add-rap")
    public String createRap(Model model){
        Rap rap = new Rap();
        model.addAttribute("rap" , rap);
        return "admin/addrap";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute("rap") Rap rap,Model model){
        Rap rap1 = rapRepository.findByTenRap(rap.getTenRap());
        if (rap1 == null){
            rapRepository.save(rap);
            return "redirect:/quantri/rap";
        }else{
            model.addAttribute("loi","Bạn đã nhập trùng tên rạp đã có sẵn! Hãy nhập lại");
            return "admin/addrap";
        }
    }
    @GetMapping("/update-rap")
    public String updateRap(@RequestParam("id") Integer id, Model model){
        Rap rap = rapRepository.findById(id).get();
        model.addAttribute("rap" , rap);
        return "admin/updaterap";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("rap") Rap rap,Model model){
            rapRepository.saveAndFlush(rap);
            return "redirect:/quantri/rap";
    }
    @GetMapping("/delete-rap")
    public String delete(@RequestParam("id") Integer id){
        rapRepository.deleteById(id);
        return "redirect:/quantri/rap";
    }
    @GetMapping("/tatcarap")
    public String showTatCaRap(Model model){
        List<String> diaChiRap = rapRepository.findDistinctDiaChi();
        model.addAttribute("diaChiRap",diaChiRap);
        return "client/tatcarap";
    }
    @GetMapping("/loadrapbydiachi")
    @ResponseBody
    public List<Rap> loadRapByDiaChi(@RequestParam("diachi") String diaChi, Model model) {
        List<Rap> raps = rapRepository.findByDiaChi(diaChi);
        return raps;
    }
    @GetMapping("/rapdacbiet")
    public String rapDacBiet(){
        return "client/rapdacbiet";
    }
}
