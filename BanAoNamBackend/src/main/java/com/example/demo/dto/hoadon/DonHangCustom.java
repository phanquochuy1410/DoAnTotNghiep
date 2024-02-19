package com.example.demo.dto.hoadon;

import java.util.Date;

public interface DonHangCustom {

    Integer getIdHoaDon();

    Integer getTrangThai();

    Integer getTongTien();

    Integer getTinhTrang();

    Integer getSoLuong();

    Date getNgayTao();

    Date getNgayNhan();

    Date getUpdateAt();

    String getLyDo();

    Integer getHinhThucThanhToan();

    Float getGiaKhuyenMai();

    Float gia();
}