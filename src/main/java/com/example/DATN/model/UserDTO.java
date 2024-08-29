package com.example.DATN.model;

import jakarta.validation.constraints.*;

public class UserDTO {
    private int id;
    @NotNull(message = "Thông tin bắt buộc")
    @Size(min = 1,message = "Độ dài tối thiểu là 1")
    private String username;
    @NotNull(message = "Thông tin bắt buộc")
    @Size(min = 8,message = "Độ dài tối thiểu là 8")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[@#$%^&+=!]).*$",
            message = "Mật khẩu phải chứa ít nhất một chữ số và một ký tự đặc biệt")
    private String password;
    @NotNull(message = "Thông tin bắt buộc")
    private String fullName;
    @NotNull(message = "Thông tin bắt buộc")
    private String sdt;
    @NotNull(message = "Thông tin bắt buộc")
    private String diaChi;
    @NotNull(message = "Thông tin bắt buộc")
    @Email(message = "Email không hợp lệ")
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UserDTO(String username, String password, String fullName, String sdt, String diaChi, String email) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.email = email;
    }

    public UserDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
