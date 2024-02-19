package com.example.demo.entity;

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

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "khuyen_mai")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class KhuyenMai {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "ngay_ket_thuc")
    private LocalDate ngayKetThuc;

    @Column(name = "chiet_khau")
    private Integer chietKhau;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "create_at")
    private LocalDateTime create_at;

    @Column(name = "update_at")
    private LocalDateTime update_at;

    @Column(name = "create_by")
    private String create_by;

    @Column(name = "update_by")
    private String update_by;

}
