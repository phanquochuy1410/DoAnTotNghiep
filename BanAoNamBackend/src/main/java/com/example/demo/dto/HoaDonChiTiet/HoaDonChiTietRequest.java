package com.example.demo.dto.HoaDonChiTiet;

import com.example.demo.entity.ChiTietSanPham;
import com.example.demo.entity.HoaDon;
import com.example.demo.entity.HoaDonChiTiet;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class HoaDonChiTietRequest {

    private String idHoaDonChiTiet;

    private String idChiTietSanPham;

    private Date ngayTao;

    private Integer soLuong;

    private Float gia;

    private Float khuyenMai;

    private Float thanhTien;

    private String idHoaDon;

    public HoaDonChiTiet map1(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTiet.setSoLuong(this.soLuong);
        hoaDonChiTiet.setIdHoaDon(HoaDon.builder().id(Integer.valueOf(idHoaDon)).build());
        hoaDonChiTiet.setIdChiTietSanPham(ChiTietSanPham.builder().id(Integer.valueOf(idChiTietSanPham)).build());
        return hoaDonChiTiet;
    }

    public HoaDonChiTiet map(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTiet.setSoLuong(Integer.valueOf(this.soLuong));
        hoaDonChiTiet.setGia(this.thanhTien);
        hoaDonChiTiet.setGiaSauKhuyenMai(this.khuyenMai);
        hoaDonChiTiet.setIdHoaDon(HoaDon.builder().id(Integer.valueOf(idHoaDon)).build());
        hoaDonChiTiet.setIdChiTietSanPham(ChiTietSanPham.builder().id(Integer.valueOf(idChiTietSanPham)).build());
        return hoaDonChiTiet;
    }
    public HoaDonChiTiet map2(HoaDonChiTiet hoaDonChiTiet) {
        hoaDonChiTiet.setSoLuong(Integer.valueOf(this.soLuong));
        hoaDonChiTiet.setGia(this.thanhTien);
        hoaDonChiTiet.setGiaSauKhuyenMai(this.khuyenMai);
        return hoaDonChiTiet;
    }

}
