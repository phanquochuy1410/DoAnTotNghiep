package com.example.demo.dto.khachhang;

import com.example.demo.entity.DiaChi;
import com.example.demo.entity.KhachHang;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class KhachHangRequest {

    private String ma;
    private String ho;
    private String tenDem;
    private String ten;
    private String soDienThoai;
    private String gioiTinh;
    private String email;
    private String matKhau;
    private String trangThai;
    private String idDiaChi;
    private LocalDate create_at;
    private LocalDate update_at;

    public KhachHang mapRegis(KhachHang kh) {
        kh.setHo(this.ho);
        kh.setTenDem(this.tenDem);
        kh.setTen(this.ten);
        kh.setSoDienThoai(this.soDienThoai);
        kh.setEmail(this.email);
        kh.setMatKhau(this.matKhau);
        kh.setTrangThai(String.valueOf(0));
        return kh;
    }

    public KhachHang map(KhachHang kh) {
        kh.setMa(this.ma);
        kh.setHo(this.ho);
        kh.setTenDem(this.tenDem);
        kh.setTen(this.ten);
        kh.setSoDienThoai(this.soDienThoai);
        kh.setGioiTinh(Boolean.valueOf(this.gioiTinh));
        kh.setEmail(this.email);
        kh.setMatKhau(this.matKhau);
        kh.setTrangThai(this.trangThai);
        kh.setIdDiaChi(DiaChi.builder().id(Integer.valueOf(idDiaChi)).build());
        return kh;
    }

}
