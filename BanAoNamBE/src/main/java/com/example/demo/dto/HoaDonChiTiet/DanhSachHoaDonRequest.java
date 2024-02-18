package com.example.demo.dto.HoaDonChiTiet;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Getter
@Setter
public class DanhSachHoaDonRequest {

    private Integer id;

    private String maHoaDon;

    private String tenKhachHang;

    private String diaChi;

    private Integer trangThai;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date ngayTao;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date ngayNhan;

    private Integer soLuong;

    private Float tongTien;

    private Integer hinhThucThanhToan;

    private String tenSanPham;

    private Integer thang;

    private String nam;

    private String lyDo;
}
