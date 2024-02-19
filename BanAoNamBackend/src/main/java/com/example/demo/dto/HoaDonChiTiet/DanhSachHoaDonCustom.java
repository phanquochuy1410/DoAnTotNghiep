package com.example.demo.dto.HoaDonChiTiet;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

public interface DanhSachHoaDonCustom {

    Integer getId();

    String getMaHoaDon();

    String getTenKhachHang();

    String getDiaChi();

    Integer getTrangThai();

    Date getNgayTao();

    Date getNgayNhan();

    Integer getSoLuong();

    Float getTongTien();

    Integer getHinhThucThanhToan();

    String getTenSanPham();

    Integer getThang();

    Integer getNam();

    String getLyDo();
}
