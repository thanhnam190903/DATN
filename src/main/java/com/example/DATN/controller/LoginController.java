package com.example.DATN.controller;

import com.example.DATN.Service.UserService;
import com.example.DATN.model.Role;
import com.example.DATN.model.User;
import com.example.DATN.model.UserDTO;
import com.example.DATN.repository.RoleRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Collection;

@Controller
public class LoginController {

    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public LoginController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/showloginpage")
    public String showLoginPage(){
        return "Login";
    }
    @GetMapping("/showPage403")
    public String showPage403(){
        return "erorr";
    }
    @GetMapping("/dangki")
    public String showFormDangKi(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("userDTO",userDTO);
        return "dangki";
    }
    @InitBinder
    public void initBinder(WebDataBinder data){
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        data.registerCustomEditor(String.class,stringTrimmerEditor);
    }
    @PostMapping("/submit-dangki")
    public String dangKi(@Valid @ModelAttribute("userDTO") UserDTO userDTO , BindingResult result , Model model ){
        String username = userDTO.getUsername();
        if (result.hasErrors()){
            return "dangki";
        }
        User userExit = userService.findByUsername(username);
        if (userExit != null){
            model.addAttribute("userDTO",new UserDTO());
            model.addAttribute("my_error","Tài khoản đã tồn tại");
            return "dangki";
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User();
        user.setFullName(userDTO.getFullName());
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setSdt(userDTO.getSdt());
        user.setEmail(userDTO.getEmail());
        user.setDiaChi(userDTO.getDiaChi());
        user.setTrangThai(1);

        Role defausRole = roleRepository.findByName("ROLE_USER");
        Collection<Role> roles = new ArrayList<>();
        roles.add(defausRole);
        user.setRoles(roles);
        userService.save(user);

        return "redirect:/showloginpage";
    }
}
