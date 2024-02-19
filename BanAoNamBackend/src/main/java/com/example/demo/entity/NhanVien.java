package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "nhan_vien")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NhanVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ho")
    private String ho;

    @Column(name = "ten_dem")
    private String tenDem;

    @Column(name = "ten")
    private String ten;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "gioi_tinh")
    private Boolean gioiTinh;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngay_sinh")
    private Date ngaySinh;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "email")
    private String email;

    @Column(name = "mat_khau")
    private String matKhau;

    @Column(name = "chuc_vu")
    private Integer chucVu;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "create_at")
    private Date create_at;

    @Column(name = "update_at")
    private Date update_at;

    @Column(name = "create_by")
    private String create_by;

    @Column(name = "update_by")
    private String update_by;

}
