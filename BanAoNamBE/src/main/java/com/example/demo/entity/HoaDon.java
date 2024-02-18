package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "hoa_don")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HoaDon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "ma")
    private String ma;

    @Column(name = "gia")
    private Float gia;

    @Column(name = "so_dien_thoai")
    private String soDienThoai;

    @Column(name = "tien_ship")
    private Float tienShip;

    @Column(name = "ten_nguoi_nhan")
    private String tenNguoiNhan;

    @Column(name = "ngay_tao")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate ngayTao;

    @Column(name = "ngay_nhan")
    private LocalDate ngayNhan;

    @Column(name = "ly_do")
    private String lyDo;

    @Column(name = "hinh_thuc_thanh_toan")
    private Integer hinhThucThanhToan;

    @Column(name = "trang_thai")
    private Integer trangThai;

    @Column(name = "tinh_trang")
    private Integer tinhTrang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khach_hang", referencedColumnName = "id")
    private KhachHang idKhachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien", referencedColumnName = "id")
    private NhanVien idNhanVien;

    @Column(name = "create_at")
    private LocalDate create_at;

    @Column(name = "update_at")
    private LocalDate update_at;

    @Column(name = "create_by")
    private String create_by;

    @Column(name = "update_by")
    private String update_by;

}
